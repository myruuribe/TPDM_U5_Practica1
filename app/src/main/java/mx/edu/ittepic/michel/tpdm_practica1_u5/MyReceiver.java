package mx.edu.ittepic.michel.tpdm_practica1_u5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    private static final String SMS_RECEIVED= "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG= "SmsBroadcastReceived";
    String msg, phoneNo= "";
    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction()==SMS_RECEIVED){
            Bundle dataBundle= intent.getExtras();
            if (dataBundle!=null){
                Object[] mypdu = (Object[])dataBundle.get("pdus");
                final SmsMessage[] message = new SmsMessage[mypdu.length];

                for (int i= 0; i<mypdu.length; i++){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                        String format = dataBundle.getString("format");
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i], format);
                    }
                    else {
                        message[i] = SmsMessage.createFromPdu((byte[])mypdu[i]);
                    }
                    msg= message[i].getMessageBody();
                    phoneNo= message[i].getOriginatingAddress();
                }
                Toast.makeText(context,"Mensaje:"+msg+"\nNumber:" +phoneNo,Toast.LENGTH_LONG).show();
                procesarmensaje();

            }
        }

    }

    public void procesarmensaje(){
        String princesa ="";

       if (msg.contains("enero")){
        princesa= "Tu princesa es Blanca Nieves! No aceptes manzanas de extrañas viejitas :)";
       }
       if (msg.contains("febrero")){
        princesa= "Tu princesa es Moana! Regresa el corazon de Tefiti";
       }if (msg.contains("marzo")){
            princesa= "Tu princesa es Cenicienta! No pierdas tu zapatilla de cristal!";
        }if (msg.contains("abril")){
            princesa= "Tu princesa es Merida! Apunta con cuidado tus flechas :)";
        }if (msg.contains("mayo")){
            princesa= "Tu princesa es AUrora! Recuerda solo dormir 8 horas diarias";
        }if (msg.contains("junio")){
            princesa= "Tu princesa es Rapunzel! Espero te gusten las luces tanto como a ella :)";
        }if (msg.contains("julio")){
            princesa= "Tu princesa es Ariel! Bajo del mar eres feliz siendo sirena";
        }if (msg.contains("agosto")){
            princesa= "Tu princesa es Mulan! Salva a toda china y no deshonres a tu vaca!";
        }if (msg.contains("septiembre")){
            princesa="Tu princesa es Tiana! No beses sapos toda tu vida, solo a un principe :)";
        }if (msg.contains("octubre")){
            princesa= "Tu princesa es Bella! Encuentra a tu amor en un bestia";
        }if (msg.contains("noviembre")){
            princesa= "Tu princesa es Jazmin! Un mundo ideal!";
        }if (msg.contains("diciembre")){
            princesa= "Tu princesa es Pocahontas! La vieja sauce espera para hanlar contigo!";
        }
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phoneNo,null,princesa,null,null);
    }
}
