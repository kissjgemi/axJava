package aBasis;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyMp3IO {

    private RandomAccessFile f;
    private ArrayList<String> myIOList = new ArrayList<>();

    public void setMyMpegVersion(MPEG_VERSION_ID myMpegVersion) {
        this.myMpegVersion = myMpegVersion;
    }

    public ArrayList<String> getMyIOList() {
        return myIOList;
    }

    public MyMp3IO(String f, String m) {
        try {
            this.f = new RandomAccessFile(f, m);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MyMp3IO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public enum MPEG_VERSION_ID {
        V2_5, RESERVED, V2, V1
    }

    private enum FRAME_CRC_PROTECTED {
        PROTECTED, NOPROTECT
    }

    private enum MPEG_LAYER_DESCRIPTION {
        RESERVED, LAYER1, LAYER2, LAYER3
    }

    private MPEG_VERSION_ID myMpegVersion;
    private MPEG_LAYER_DESCRIPTION myLayerDescription;
    private FRAME_CRC_PROTECTED myCrcProtection;

    private MPEG_VERSION_ID getMyVersionId(int in) {
        switch (in & 24) {
            case 24: {
                return MPEG_VERSION_ID.V1;
            }
            case 16: {
                return MPEG_VERSION_ID.V2;
            }
            case 8: {
                return MPEG_VERSION_ID.RESERVED;
            }
            default: {
                return MPEG_VERSION_ID.V2_5;
            }
        }
    }

    private MPEG_LAYER_DESCRIPTION getMyLayerDescription(int in) {
        switch (in & 6) {
            case 6: {
                return MPEG_LAYER_DESCRIPTION.LAYER1;
            }
            case 4: {
                return MPEG_LAYER_DESCRIPTION.LAYER2;
            }
            case 2: {
                return MPEG_LAYER_DESCRIPTION.LAYER3;
            }
            default: {
                return MPEG_LAYER_DESCRIPTION.RESERVED;
            }
        }
    }

    private FRAME_CRC_PROTECTED getMyCrcProtection(int in) {
        return ((in & 1) != 1) ? FRAME_CRC_PROTECTED.PROTECTED
                : FRAME_CRC_PROTECTED.NOPROTECT;
    }

    private int[] bitRateV1L1 = {0, 32, 64,
        96, 128, 160, 192, 224, 256, 288, 320, 352, 384, 416, 448, 0};

    private int[] bitRateV1L2 = {0, 32, 48,
        56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384, 0};

    private int[] bitRateV1L3 = {0, 32, 40,
        48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 0};

    private int[] bitRateV2L1 = {0, 32, 48,
        56, 64, 80, 96, 112, 128, 144, 160, 176, 192, 224, 256, 0};

    private int[] bitRateV2L2_3 = {0, 8, 16,
        24, 32, 40, 48, 56, 64, 80, 96, 112, 128, 144, 160, 0};

    private int myBitRate;

    private int getMyBitRate(int in) {
        int index = (in & 0xFF) / 16;
        switch (myMpegVersion) {
            case V1: {
                switch (myLayerDescription) {
                    case LAYER1: {
                        return bitRateV1L1[index];
                    }
                    case LAYER2: {
                        return bitRateV1L2[index];
                    }
                    case LAYER3: {
                        return bitRateV1L3[index];
                    }
                    default: {
                        return 0;
                    }
                }
            }
            case V2:
            case V2_5: {
                switch (myLayerDescription) {
                    case LAYER1: {
                        return bitRateV2L1[index];
                    }
                    case LAYER2:
                    case LAYER3: {
                        return bitRateV2L2_3[index];
                    }
                    default: {
                        return 0;
                    }
                }
            }
            default: {
                return 0;
            }
        }
    }

    private int[] samplingRateV1 = {44100, 48000, 32000, 0};
    private int[] samplingRateV2 = {22050, 24000, 16000, 0};
    private int[] samplingRateV2_5 = {11025, 12000, 8000, 0};

    private int mySamplingRate;

    private int getMySamplingRate(int in) {
        int index = (in & 0x0F) / 4;
        switch (myMpegVersion) {
            case V1: {
                return samplingRateV1[index];
            }
            case V2: {
                return samplingRateV2[index];
            }
            case V2_5: {
                return samplingRateV2_5[index];
            }
            default:
                return 0;
        }
    }

    private int myPaddingBit;

    private int getMyPaddingBit(int in) {
        return ((in & 2) == 2) ? 1 : 0;
    }

    private enum PRIVATE_BIT {
        SET, NON
    }
    private PRIVATE_BIT myPrivateBit;

    private PRIVATE_BIT getMyPrivateBit(int in) {
        return ((in & 1) == 1) ? PRIVATE_BIT.SET : PRIVATE_BIT.NON;
    }

    private int myFrameLength;

    private int getMyFrameLength() {
        switch (myLayerDescription) {
            case LAYER1: {
                return (12 * myBitRate * 1000 / mySamplingRate + myPaddingBit) * 4;
            }
            case LAYER2:
            case LAYER3: {
                return 144 * myBitRate * 1000 / mySamplingRate + myPaddingBit;
            }
            default:
                return 0;
        }
    }

    private enum SOUND_QUALITY {
        STEREO, JOINT_STEREO, DUAL_CHANNEL, SINGLE_CHANNEL
    }

    private SOUND_QUALITY mySoundQuality;

    private SOUND_QUALITY getMySoundQuality(int in) {
        switch (in & 192) {
            case 192: {
                return SOUND_QUALITY.SINGLE_CHANNEL;
            }
            case 128: {
                return SOUND_QUALITY.DUAL_CHANNEL;
            }
            case 64: {
                return SOUND_QUALITY.JOINT_STEREO;
            }
            default:
                return SOUND_QUALITY.STEREO;
        }
    }

    private enum FREQUENCY_BANDS {
        BANDS_FROM_4, BANDS_FROM_8,
        BANDS_FROM12, BANDS_FROM16,
        INTENSITY_STEREO, MS_STEREO,
        INTEMSITY_MS_STEREO, NONE
    }

    private FREQUENCY_BANDS myFrequencyBands;

    private FREQUENCY_BANDS getMyFrequencyBands(int in) {
        if (mySoundQuality == SOUND_QUALITY.JOINT_STEREO) {
            switch (in & 48) {
                case 0: {
                    return (myLayerDescription == MPEG_LAYER_DESCRIPTION.LAYER3)
                            ? FREQUENCY_BANDS.NONE : FREQUENCY_BANDS.BANDS_FROM_4;
                }
                case 16: {
                    return (myLayerDescription == MPEG_LAYER_DESCRIPTION.LAYER3)
                            ? FREQUENCY_BANDS.INTENSITY_STEREO
                            : FREQUENCY_BANDS.BANDS_FROM_8;
                }
                case 32: {
                    return (myLayerDescription == MPEG_LAYER_DESCRIPTION.LAYER3)
                            ? FREQUENCY_BANDS.MS_STEREO
                            : FREQUENCY_BANDS.BANDS_FROM12;
                }
                case 48: {
                    return (myLayerDescription == MPEG_LAYER_DESCRIPTION.LAYER3)
                            ? FREQUENCY_BANDS.INTEMSITY_MS_STEREO
                            : FREQUENCY_BANDS.BANDS_FROM16;
                }
            }
        }
        return FREQUENCY_BANDS.NONE;
    }

    private enum RECORD_COPYRIGHT {
        COPYRIGHTED, NOCOPYRIGHT
    }

    private RECORD_COPYRIGHT myCopyright;

    private RECORD_COPYRIGHT getMyCopyright(int in) {
        return ((in & 8) == 8)
                ? RECORD_COPYRIGHT.COPYRIGHTED : RECORD_COPYRIGHT.NOCOPYRIGHT;
    }

    private enum RECORD_ORIGIN {
        COPY_OF_ORIGIN, ORIGINAL_MEDIA
    }

    private RECORD_ORIGIN myCopyOrigin;

    private RECORD_ORIGIN getMyCopyOrigin(int in) {
        return ((in & 4) == 4)
                ? RECORD_ORIGIN.ORIGINAL_MEDIA : RECORD_ORIGIN.COPY_OF_ORIGIN;
    }

    private enum EMPHASIS {
        NONE, MSEC50_15, RESERVED, CCITJ7
    }

    private EMPHASIS myEmphasis;

    private EMPHASIS getMyEmphasis(int in) {
        switch (in & 3) {
            case 3: {
                return EMPHASIS.CCITJ7;
            }
            case 2: {
                return EMPHASIS.RESERVED;
            }
            case 31: {
                return EMPHASIS.MSEC50_15;
            }
            default:
                return EMPHASIS.NONE;
        }
    }

    private void bitWrite(byte in) {
        int dummy = (in & 0xFF);
        String str = "";
        if ((dummy & 128) == 128) {
            str += "1";
        } else {
            str += "0";
        }
        if ((dummy & 64) == 64) {
            str += "1";
        } else {
            str += "0";
        }
        if ((dummy & 32) == 32) {
            str += "1";
        } else {
            str += "0";
        }
        if ((dummy & 16) == 16) {
            str += "1";
        } else {
            str += "0";
        }
        if ((dummy & 8) == 8) {
            str += "1";
        } else {
            str += "0";
        }
        if ((dummy & 4) == 4) {
            str += "1";
        } else {
            str += "0";
        }
        if ((dummy & 2) == 2) {
            str += "1";
        } else {
            str += "0";
        }
        if ((dummy & 1) == 1) {
            str += "1";
        } else {
            str += "0";
        }
        System.out.println(" " + str);
    }

    private void showMyProperties(byte b1, byte b2, byte b3) {
        System.out.println("1: FrameHeader: 11111111");
        System.out.printf("2: %11s:", b1);
        bitWrite(b1);
        System.out.printf(" %8s", myMpegVersion);
        System.out.printf(" %8s", myLayerDescription);
        System.out.print(" " + myCrcProtection + "\n");

        System.out.printf("3: %11s:", b2);
        bitWrite(b2);
        System.out.printf(" %3dkbps", myBitRate);
        System.out.printf(" %5dHz", mySamplingRate);
        System.out.print(" +" + myPaddingBit);
        System.out.print(" " + myPrivateBit);
        System.out.print(" Framelength: " + myFrameLength + "byte\n");

        System.out.printf("4: %11s:", b3);
        bitWrite(b3);
        System.out.print(" " + mySoundQuality);
        System.out.print(" " + myFrequencyBands);
        System.out.print(" " + myCopyright);
        System.out.print(" " + myCopyOrigin);
        System.out.print(" " + myEmphasis
                + "\n----------------\n");
    }

    private void showMyHeaderBytes(byte b1, byte b2, byte b3) {
        System.out.print("B1:   -1, ");
        System.out.printf("B2: %4s, ", b1);
        System.out.printf("B3: %4s, ", b2);
        System.out.printf("B4: %4s.\n", b3);
    }

    public class myFrameHeader {

        private final byte[] MY4BYTES = new byte[4];

        public byte[] getMY4BYTES() {
            return MY4BYTES;
        }

        public myFrameHeader(byte b1, byte b2, byte b3) {
            this.MY4BYTES[0] = (byte) 255;
            this.MY4BYTES[1] = (byte) b1;
            this.MY4BYTES[2] = (byte) b2;
            this.MY4BYTES[3] = (byte) b3;
        }
    }

    private final ArrayList<myFrameHeader> MYFRAMEHEADERS
            = new ArrayList<>();

    public ArrayList<myFrameHeader> getMYFRAMEHEADERS() {
        return MYFRAMEHEADERS;
    }

    private void getProperties(byte b1, byte b2, byte b3) {
        myMpegVersion = getMyVersionId(b1);
        myLayerDescription = getMyLayerDescription(b1);
        myCrcProtection = getMyCrcProtection(b1);

        myBitRate = getMyBitRate(b2);
        mySamplingRate = getMySamplingRate(b2);
        myPaddingBit = getMyPaddingBit(b2);
        myPrivateBit = getMyPrivateBit(b2);
        myFrameLength = getMyFrameLength();

        mySoundQuality = getMySoundQuality(b3);
        myFrequencyBands = getMyFrequencyBands(b3);
        myCopyright = getMyCopyright(b3);
        myCopyOrigin = getMyCopyOrigin(b3);
        myEmphasis = getMyEmphasis(b3);

        MYFRAMEHEADERS.add(new myFrameHeader(b1, b2, b3));

        if (showProperties) {
            showMyProperties(b1, b2, b3);
        } else {
            showMyHeaderBytes(b1, b2, b3);
        }
    }

    public class ID3v1Tag {

        public final String[] GENRES = {
            "Blues",
            "Classic Rock",
            "Country",
            "Dance",
            "Disco",
            "Funk",
            "Grunge",
            "Hip-Hop",
            "Jazz",
            "Metal",
            "New Age",
            "Oldies",
            "Other",
            "Pop",
            "R&B",
            "Rap",
            "Reggae",
            "Rock",
            "Techno",
            "Industrial",
            "Alternative",
            "Ska",
            "Death Metal",
            "Pranks",
            "Soundtrack",
            "Euro-Techno",
            "Ambient",
            "Trip-Hop",
            "Vocal",
            "Jazz+Funk",
            "Fusion",
            "Trance",
            "Classical",
            "Instrumental",
            "Acid",
            "House",
            "Game",
            "Sound Clip",
            "Gospel",
            "Noise",
            "Alt Rock",
            "Bass",
            "Soul",
            "Punk",
            "Space",
            "Meditative",
            "Instrumental Pop",
            "Instrumental Rock",
            "Ethnic",
            "Gothic",
            "Darkwave",
            "Techno-Industrial",
            "Electronic",
            "Pop-Folk",
            "Eurodance",
            "Dream",
            "Southern Rock",
            "Comedy",
            "Cult",
            "Gangsta",
            "Top 40",
            "Christian Rap",
            "Pop/Funk",
            "Jungle",
            "Native American",
            "Cabaret",
            "New Wave",
            "Psychedelic",
            "Rave",
            "Showtunes",
            "Trailer",
            "Lo-Fi",
            "Tribal",
            "Acid Punk",
            "Acid Jazz",
            "Polka",
            "Retro",
            "Musical",
            "Rock & Roll",
            "Hard Rock",
            "Folk",
            "Folk/Rock",
            "National Folk",
            "Swing",
            "Fast Fusion",
            "Bebob",
            "Latin",
            "Revival",
            "Celtic",
            "Bluegrass",
            "Avantgarde",
            "Gothic Rock",
            "Progressive Rock",
            "Psychedelic Rock",
            "Symphonic Rock",
            "Slow Rock",
            "Big Band",
            "Chorus",
            "Easy Listening",
            "Acoustic",
            "Humour",
            "Speech",
            "Chanson",
            "Opera",
            "Chamber Music",
            "Sonata",
            "Symphony",
            "Booty Bass",
            "Primus",
            "Porn Groove",
            "Satire/Parody",
            "Slow Jam",
            "Club",
            "Tango",
            "Samba",
            "Folklore",
            "Ballad",
            "Power Ballad",
            "Rhythmic Soul",
            "Freestyle",
            "Duet",
            "Punk Rock",
            "Drum Solo",
            "Acapella",
            "Euro-House",
            "Dance Hall",
            "Goa",
            "Drum & Bass",
            "Club-House",
            "Hardcore",
            "Terror",
            "Indie",
            "BritPop",
            "Negerpunk",
            "Polsk Punk",
            "Beat",
            "Christian Gangsta",
            "Heavy Metal",
            "Black Metal",
            "Crossover",
            "Contemporary Chr",
            "Christian Rock",
            "Merengue",
            "Salsa",
            "Thrash Metal",
            "Anime",
            "JPop",
            "Synthpop"
        };
        public static final int TAG_LENGTH = 128;

        private static final String VERSION_0 = "0";
        private static final String VERSION_1 = "1";
        private static final String TAG_NAME = "TAG";
        private static final int TITLE_OFFSET = 3;
        private static final int TITLE_LENGTH = 30;
        private static final int ARTIST_OFFSET = 33;
        private static final int ARTIST_LENGTH = 30;
        private static final int ALBUM_OFFSET = 63;
        private static final int ALBUM_LENGTH = 30;
        private static final int YEAR_OFFSET = 93;
        private static final int YEAR_LENGTH = 4;
        private static final int COMMENT_OFFSET = 97;
        private static final int COMMENT_LENGTH_V1_0 = 30;
        private static final int COMMENT_LENGTH_V1_1 = 28;
        private static final int TRACK_MARKER_OFFSET = 125;
        private static final int TRACK_OFFSET = 126;
        private static final int GENRE_OFFSET = 127;

        private String track = null;
        private String artist = null;
        private String title = null;
        private String album = null;
        private String year = null;
        private int genre = -1;
        private String comment = null;

        public ID3v1Tag() {
        }

        public ID3v1Tag(byte[] bytes) {
            unpackTag(bytes);
        }

        private String bytes2String(byte[] bytes, int first, int length) {
            String toReturn = "";
            for (int ii = 0; ii < length; ii++) {
                toReturn += String.valueOf((char) bytes[first + ii]);
            }
            return toReturn;
        }

        private void unpackTag(byte[] bytes) {
            sanityCheckTag(bytes);
            title = "title";
            artist = "artist";
            album = "album";
            year = "year";
            genre = bytes[GENRE_OFFSET] & 0xFF;
            if (genre == 0xFF) {
                genre = -1;
            }
            if (bytes[TRACK_MARKER_OFFSET] != 0) {
                comment = "comment";
                track = null;
            } else {
                comment = "comment";
                int trackInt = bytes[TRACK_OFFSET];
                if (trackInt == 0) {
                    track = "";
                } else {
                    track = Integer.toString(trackInt);
                }
            }
        }

        private void sanityCheckTag(byte[] bytes) {
            if (bytes.length != TAG_LENGTH) {
                try {
                    throw new Exception("Buffer length wrong");
                } catch (Exception ex) {
                    System.out.println("sanityCheckTag Error" + ex.getMessage());
                }
            }
            String tagIentity = bytes2String(bytes, 0, TAG_NAME.length());
            if (!TAG_NAME.equals(tagIentity)) {
                try {
                    throw new Exception("TAG ID is wrong");
                } catch (Exception ex) {
                    System.out.println("sanityCheckTag Error" + ex.getMessage());
                }
            }
        }
    }

    public long getMpegDatas(long start, byte b) {
        long toReturn = start;
        byte in;
        int breakline = 0;
        try {
            long fileLength = f.length();
            while (start < fileLength) {
                f.seek(start);
                while (f.getFilePointer() < fileLength) {
                    if (!Objects.equals(b, in = f.readByte())) {
                        if (breakline == 10) {
                            breakline = 1;
                            System.out.println("");
                        } else {
                            breakline++;
                        }
                        System.out.printf(" %4s %2s", in, (char) in);
                    } else {
                        if (breakline > 1) {
                            System.out.println("");
                        }
                        break;
                    }
                }
                breakline = 0;
                if (f.getFilePointer() == fileLength) {
                    System.out.printf("\nEnd> %8d.\n", f.getFilePointer());
                    break;
                }
                System.out.printf("Start> %8d. -> ", start);
                in = f.readByte();
                if (((in & 0xFF)) >= 224) {
                    getProperties(in, f.readByte(), f.readByte());
                }
                start = f.getFilePointer() + myFrameLength - 4;
            }
            System.out.println("Count of frames: " + MYFRAMEHEADERS.size());
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

        return toReturn;
    }

    public void inputFile() {
        try {
            String line;
            while ((line = f.readLine()) != null) {
                myIOList.add(line);
            }
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void appendFile(String str) {
        try {
            f.seek(f.length());//a fájlmutatót a fájl végére mozgatja
            f.write(str.getBytes());
            f.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public void deleteFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    private static boolean showProperties = false;

    public static void setShowProperties(boolean showProperties) {
        MyMp3IO.showProperties = showProperties;
    }

    public static void main(String[] args) {
        String fileName;
        fileName = "src/main/resources/music/flowers.mp3";
        fileName = "src/main/resources/music/matyas_mesek_focim_dala.mp3";
        //showProperties = true;
        String mode = "r";
        MyMp3IO myIOclass = new MyMp3IO(fileName, mode);
        long pos = 0;
        myIOclass.getMpegDatas(pos, (byte) 0b11111111);

    }
}
