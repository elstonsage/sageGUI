package sage;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */

public class Cmd2
{

    protected static final int Tc = 21603;
    protected static final int Tw = 21623;
    protected static final int Tz = 21626;
    protected static final int TL = 21580;
    protected static final int Tf = 21606;
    protected static final int Tr = 21618;
    protected static final int Ts = 21619;
    protected static final int Td = 21604;
    protected static final int TD = 21572;
    protected static final int Tm = 21613;
    protected static final int Tstar = 21546;
    protected static final int Tj = 21610;
    protected static final int TJ = 21578;
    protected static final int quote = 39;
    protected static final int doubleQuote = 34;
    protected static final int BI = 16969;
    protected static final int ID = 18756;
    protected static final int m = 109;
    protected static final int l = 108;
    protected static final int c = 99;
    protected static final int d = 100;
    protected static final int v = 118;
    protected static final int y = 121;
    protected static final int h = 104;
    protected static final int re = 29285;
    protected static final int S = 83;
    protected static final int s = 115;
    protected static final int f = 102;
    protected static final int F = 70;
    protected static final int fstar = 26154;
    protected static final int Fstar = 17962;
    protected static final int B = 66;
    protected static final int Bstar = 16938;
    protected static final int b = 98;
    protected static final int bstar = 25130;
    protected static final int n = 110;
    protected static final int W = 87;
    protected static final int Wstar = 22314;
    protected static final int BT = 16980;
    protected static final int ET = 17748;
    protected static final int Do = 17519;
    protected static final int w = 119;
    protected static final int j = 106;
    protected static final int J = 74;
    protected static final int M = 77;
    protected static final int ri = 29289;
    protected static final int i = 105;
    protected static final int gs = 26483;
    protected static final int q = 113;
    protected static final int Q = 81;
    protected static final int cm = 25453;
    protected static final int d0 = 25648;
    protected static final int d1 = 25649;
    protected static final int cs = 25459;
    protected static final int CS = 17235;
    protected static final int sc = 29539;
    protected static final int scn = 0x73636e;
    protected static final int SC = 21315;
    protected static final int SCN = 0x53434e;
    protected static final int g = 103;
    protected static final int G = 71;
    protected static final int rg = 29287;
    protected static final int RG = 21063;
    protected static final int k = 107;
    protected static final int K = 75;
    protected static final int sh = 29544;
    protected static final int BMC = 0x424d43;
    protected static final int BDC = 0x424443;
    protected static final int EMC = 0x454d43;
    protected static final int MP = 19792;
    protected static final int DP = 17488;
    protected static final int BX = 16984;
    protected static final int EX = 17752;

    public Cmd2()
    {
    }

    protected static int getCommandID(int i1)
    {
        int j1 = -1;
        switch(i1)
        {
        case 21603:
            j1 = 21603;
            break;

        case 21623:
            j1 = 21623;
            break;

        case 21626:
            j1 = 21626;
            break;

        case 21580:
            j1 = 21580;
            break;

        case 21606:
            j1 = 21606;
            break;

        case 21618:
            j1 = 21618;
            break;

        case 21619:
            j1 = 21619;
            break;

        case 21604:
            j1 = 21604;
            break;

        case 21572:
            j1 = 21572;
            break;

        case 21613:
            j1 = 21613;
            break;

        case 21546:
            j1 = 21546;
            break;

        case 21610:
            j1 = 21610;
            break;

        case 21578:
            j1 = 21578;
            break;

        case 39: // '\''
            j1 = 39;
            break;

        case 34: // '"'
            j1 = 34;
            break;

        case 16969:
            j1 = 16969;
            break;

        case 18756:
            j1 = 18756;
            break;

        case 109: // 'm'
            j1 = 109;
            break;

        case 108: // 'l'
            j1 = 108;
            break;

        case 99: // 'c'
            j1 = 99;
            break;

        case 100: // 'd'
            j1 = 100;
            break;

        case 118: // 'v'
            j1 = 118;
            break;

        case 121: // 'y'
            j1 = 121;
            break;

        case 104: // 'h'
            j1 = 104;
            break;

        case 29285:
            j1 = 29285;
            break;

        case 83: // 'S'
            j1 = 83;
            break;

        case 115: // 's'
            j1 = 115;
            break;

        case 102: // 'f'
            j1 = 102;
            break;

        case 70: // 'F'
            j1 = 70;
            break;

        case 26154:
            j1 = 26154;
            break;

        case 17962:
            j1 = 17962;
            break;

        case 66: // 'B'
            j1 = 66;
            break;

        case 16938:
            j1 = 16938;
            break;

        case 98: // 'b'
            j1 = 98;
            break;

        case 25130:
            j1 = 25130;
            break;

        case 110: // 'n'
            j1 = 110;
            break;

        case 87: // 'W'
            j1 = 87;
            break;

        case 22314:
            j1 = 22314;
            break;

        case 16980:
            j1 = 16980;
            break;

        case 17748:
            j1 = 17748;
            break;

        case 17519:
            j1 = 17519;
            break;

        case 119: // 'w'
            j1 = 119;
            break;

        case 106: // 'j'
            j1 = 106;
            break;

        case 74: // 'J'
            j1 = 74;
            break;

        case 77: // 'M'
            j1 = 77;
            break;

        case 29289:
            j1 = 29289;
            break;

        case 105: // 'i'
            j1 = 105;
            break;

        case 26483:
            j1 = 26483;
            break;

        case 113: // 'q'
            j1 = 113;
            break;

        case 81: // 'Q'
            j1 = 81;
            break;

        case 25453:
            j1 = 25453;
            break;

        case 25648:
            j1 = 25648;
            break;

        case 25649:
            j1 = 25649;
            break;

        case 25459:
            j1 = 25459;
            break;

        case 17235:
            j1 = 17235;
            break;

        case 29539:
            j1 = 29539;
            break;

        case 7562094:
            j1 = 0x73636e;
            break;

        case 21315:
            j1 = 21315;
            break;

        case 5456718:
            j1 = 0x53434e;
            break;

        case 103: // 'g'
            j1 = 103;
            break;

        case 71: // 'G'
            j1 = 71;
            break;

        case 29287:
            j1 = 29287;
            break;

        case 21063:
            j1 = 21063;
            break;

        case 107: // 'k'
            j1 = 107;
            break;

        case 75: // 'K'
            j1 = 75;
            break;

        case 29544:
            j1 = 29544;
            break;

        case 4345155:
            j1 = 0x424d43;
            break;

        case 4342851:
            j1 = 0x424443;
            break;

        case 4541763:
            j1 = 0x454d43;
            break;

        case 19792:
            j1 = 19792;
            break;

        case 17488:
            j1 = 17488;
            break;

        case 16984:
            j1 = 16984;
            break;

        case 17752:
            j1 = 17752;
            break;
        }
        return j1;
    }

    protected static String getCommandAsString(int i1)
    {
        String s1 = "";
        switch(i1)
        {
        case 21603:
            s1 = "Tc";
            break;

        case 21623:
            s1 = "Tw";
            break;

        case 21626:
            s1 = "Tz";
            break;

        case 21580:
            s1 = "TL";
            break;

        case 21606:
            s1 = "Tf";
            break;

        case 21618:
            s1 = "Tr";
            break;

        case 21619:
            s1 = "Ts";
            break;

        case 21604:
            s1 = "Td";
            break;

        case 21572:
            s1 = "TD";
            break;

        case 21613:
            s1 = "Tm";
            break;

        case 21546:
            s1 = "Tstar";
            break;

        case 21610:
            s1 = "Tj";
            break;

        case 21578:
            s1 = "TJ";
            break;

        case 39: // '\''
            s1 = "'";
            break;

        case 34: // '"'
            s1 = "\"";
            break;

        case 16969:
            s1 = "BI";
            break;

        case 18756:
            s1 = "ID";
            break;

        case 109: // 'm'
            s1 = "m";
            break;

        case 108: // 'l'
            s1 = "l";
            break;

        case 99: // 'c'
            s1 = "c";
            break;

        case 100: // 'd'
            s1 = "d";
            break;

        case 118: // 'v'
            s1 = "v";
            break;

        case 121: // 'y'
            s1 = "y";
            break;

        case 104: // 'h'
            s1 = "h";
            break;

        case 29285:
            s1 = "re";
            break;

        case 83: // 'S'
            s1 = "S";
            break;

        case 115: // 's'
            s1 = "s";
            break;

        case 102: // 'f'
            s1 = "f";
            break;

        case 70: // 'F'
            s1 = "F";
            break;

        case 26154:
            s1 = "f*";
            break;

        case 17962:
            s1 = "F*";
            break;

        case 66: // 'B'
            s1 = "B";
            break;

        case 16938:
            s1 = "B*";
            break;

        case 98: // 'b'
            s1 = "b";
            break;

        case 25130:
            s1 = "b*";
            break;

        case 110: // 'n'
            s1 = "n";
            break;

        case 87: // 'W'
            s1 = "W";
            break;

        case 22314:
            s1 = "W*";
            break;

        case 16980:
            s1 = "BT";
            break;

        case 17748:
            s1 = "ET";
            break;

        case 17519:
            s1 = "Do";
            break;

        case 119: // 'w'
            s1 = "w";
            break;

        case 106: // 'j'
            s1 = "j";
            break;

        case 74: // 'J'
            s1 = "J";
            break;

        case 77: // 'M'
            s1 = "M";
            break;

        case 29289:
            s1 = "ri";
            break;

        case 105: // 'i'
            s1 = "i";
            break;

        case 26483:
            s1 = "gs";
            break;

        case 113: // 'q'
            s1 = "q";
            break;

        case 81: // 'Q'
            s1 = "Q";
            break;

        case 25453:
            s1 = "cm";
            break;

        case 25648:
            s1 = "d0";
            break;

        case 25649:
            s1 = "d1";
            break;

        case 25459:
            s1 = "cs";
            break;

        case 17235:
            s1 = "CS";
            break;

        case 29539:
            s1 = "sc";
            break;

        case 7562094:
            s1 = "scn";
            break;

        case 21315:
            s1 = "SC";
            break;

        case 5456718:
            s1 = "SCN";
            break;

        case 103: // 'g'
            s1 = "g";
            break;

        case 71: // 'G'
            s1 = "G";
            break;

        case 29287:
            s1 = "rg";
            break;

        case 21063:
            s1 = "RG";
            break;

        case 107: // 'k'
            s1 = "k";
            break;

        case 75: // 'K'
            s1 = "K";
            break;

        case 29544:
            s1 = "sh";
            break;

        case 4345155:
            s1 = "BMC";
            break;

        case 4342851:
            s1 = "BDC";
            break;

        case 4541763:
            s1 = "EMC";
            break;

        case 19792:
            s1 = "MP";
            break;

        case 17488:
            s1 = "DP";
            break;

        case 16984:
            s1 = "BX";
            break;

        case 17752:
            s1 = "EX";
            break;
        }
        return s1;
    }

}
