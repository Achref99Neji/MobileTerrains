/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 *
 * @author user
 */
public class TwilioSMS {
    
// Twilio account credentials
    private static final String ACCOUNT_SID = "AC3e373431593aeafe6eae3f9e6f983c2f";
    private static final String AUTH_TOKEN = "60171f95c5e2c78202d8013102967e5f";

    // Twilio phone number
    private static final String TWILIO_NUMBER = "+15673131504";

    public static void sendSMS(String toNumber, String messageBody) {

        // Initialize Twilio client
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Create message
        Message message = Message.creator(
                new PhoneNumber(toNumber),  // Destination phone number
                new PhoneNumber(TWILIO_NUMBER),  // Twilio phone number
                messageBody)  // Message body
            .create();

        // Print message SID on success
        System.out.println("Message SID: " + message.getSid());
    }
}
