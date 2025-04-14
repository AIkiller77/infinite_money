package net.lingala.zip4j.crypto.PBKDF2;

class PBKDF2HexFormatter {
    PBKDF2HexFormatter() {
    }

    public boolean fromString(PBKDF2Parameters pBKDF2Parameters, String str) {
        PBKDF2Parameters pBKDF2Parameters2 = pBKDF2Parameters;
        String str2 = str;
        if (pBKDF2Parameters2 == null || str2 == null) {
            return true;
        }
        String[] split = str2.split(":");
        if (split == null || split.length != 3) {
            return true;
        }
        byte[] hex2bin = BinTools.hex2bin(split[0]);
        int parseInt = Integer.parseInt(split[1]);
        byte[] hex2bin2 = BinTools.hex2bin(split[2]);
        pBKDF2Parameters2.setSalt(hex2bin);
        pBKDF2Parameters2.setIterationCount(parseInt);
        pBKDF2Parameters2.setDerivedKey(hex2bin2);
        return false;
    }

    public String toString(PBKDF2Parameters pBKDF2Parameters) {
        StringBuilder sb;
        PBKDF2Parameters pBKDF2Parameters2 = pBKDF2Parameters;
        new StringBuilder();
        return sb.append(BinTools.bin2hex(pBKDF2Parameters2.getSalt())).append(":").append(String.valueOf(pBKDF2Parameters2.getIterationCount())).append(":").append(BinTools.bin2hex(pBKDF2Parameters2.getDerivedKey())).toString();
    }
}
