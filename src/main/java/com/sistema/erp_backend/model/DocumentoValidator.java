package com.sistema.erp_backend.model;

public class DocumentoValidator {

    private static final String DNI_REGEX = "^[0-9]{8}$";
    private static final String RUC_FORMAT_REGEX = "^(10|15|17|20)[0-9]{9}$";
    private static final String CE_REGEX = "^[a-zA-Z0-9]{9,12}$";
    private static final String PASAPORTE_REGEX = "^[a-zA-Z0-9]{6,12}$";


    public static boolean isValid(String tipoDocumento, String numeroDocumento) {
        if(tipoDocumento == null || numeroDocumento == null) {
            return false;
        }
        String numeroLimpio = numeroDocumento.trim();
        String tipo = tipoDocumento.trim().toUpperCase();

        return switch (tipo) {
            case "DNI" -> numeroLimpio.matches(DNI_REGEX);
            case "RUC" -> numeroLimpio.matches(RUC_FORMAT_REGEX);
            case "CE" -> numeroLimpio.matches(CE_REGEX);
            case "PASAPORTE" -> numeroLimpio.matches(PASAPORTE_REGEX);
            default -> false;
        };
    }

    public static boolean validarDni(String dni) {
        return dni != null && dni.matches(DNI_REGEX);
    }

    public static boolean validarCE(String ce) {
        return ce != null && ce.matches(CE_REGEX);
    }

    public static boolean validarPasaporte(String pasaporte) {
        return pasaporte != null && pasaporte.matches(PASAPORTE_REGEX);
    }


    public static boolean validarRuc(String ruc) {
        if (ruc == null || !ruc.matches(RUC_FORMAT_REGEX)) {
            return false;
        }

        // Algoritmo de Módulo 11 para el dígito verificador del RUC
        int[] factores = {5, 4, 3, 2, 7, 6, 5, 4, 3, 2};
        int suma = 0;

        for (int i = 0; i < 10; i++) {
            int digito = Character.getNumericValue(ruc.charAt(i));
            suma += digito * factores[i];
        }

        int residuo = suma % 11;
        int digitoVerificadorCalculado = 11 - residuo;

        if (digitoVerificadorCalculado == 10) {
            digitoVerificadorCalculado = 0;
        } else if (digitoVerificadorCalculado == 11) {
            digitoVerificadorCalculado = 1;
        }

        int digitoVerificadorReal = Character.getNumericValue(ruc.charAt(10));
        return digitoVerificadorCalculado == digitoVerificadorReal;
    }


}
