import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Main {

    private static String cifrado = "AES";
    private static SecretKey obtenerClaveOpaca(int longitud){
        return null;
    }

    // Se puede acceder a sus partes. Se le llama Spec. Transparente.
    public static SecretKeySpec obtenrClaveTransparente(String miClave){
        return null;
    }

    public static String encriptar(String mensaje, SecretKey clave){
        return null;
    }

    public static String desencriptar(String criptograma, SecretKey clave){
        return null;
    }

    public static void main(String[] args) {
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
