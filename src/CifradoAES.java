import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CifradoAES {

    private static String cifrado = "AES";
    private static SecretKey obtenerClaveOpaca(int longitud) throws NoSuchAlgorithmException {
        KeyGenerator claveInstancia = KeyGenerator.getInstance(cifrado);
        claveInstancia.init(longitud); // Por defecto es 128b.
        return claveInstancia.generateKey(); // Superclave que no se puede desencriptar (hoy en día).
    }

    // Se puede acceder a sus partes. Se le llama Spec. Transparente.
    public static SecretKeySpec obtenrClaveTransparente(String miClave) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        byte[] miClaveEnBytes =  messageDigest.digest(miClave.getBytes("utf8")); // Múltiplo de 16
        return null;
    }

    public static String encriptar(String mensaje, SecretKey clave) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(cifrado);
        cipher.init(Cipher.ENCRYPT_MODE, clave);
        byte[] encVal = cipher.doFinal(mensaje.getBytes("utf8"));
        return Base64.getEncoder().encodeToString(encVal);  // Se necesitan que los bloques sean uniformes y múltiplo de 16.
    }

    public static String desencriptar(String criptograma, SecretKey clave) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher c = Cipher.getInstance(cifrado);
        c.init(Cipher.DECRYPT_MODE, clave);
        byte[] decValue = Base64.getDecoder().decode(criptograma);
        byte[] decryptedValue = c.doFinal(decValue);
        return new String(decryptedValue);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, UnsupportedEncodingException {
        String mensaje = "El método calvá junto con el sort y el wear.";
        String miClave = "Voy a encriptar una mierda con esto.";

        SecretKey miClaveOpaca = obtenerClaveOpaca(256);

        // Atención: por defecto solo para que funcione en tamaños mayores (192 o 256) hay que instalar las restricciones JAVA Cryptography Extension (JCE)
        // Unlimited Strength Jurisdiction Policy Files 8
        // En caso de usar OpenJDK, no hace falta, fuck the USA.

        System.out.println("Mensaje claro: " + mensaje);
        String criptograma = encriptar(mensaje, miClaveOpaca);

        System.out.println("Criptograma: " + criptograma);
        System.out.println("Desencriptando: " + desencriptar(criptograma, miClaveOpaca));
    }
}
