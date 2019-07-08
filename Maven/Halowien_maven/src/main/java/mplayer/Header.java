/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mplayer;

/**
 *
 * @author b6dmin
 */
public final class Header {

    /**
     * Frequencies
     */
    public static final int[][] FREQUENCIES
            = {{22050, 24000, 16000, 1},
            {44100, 48000, 32000, 1},
            {11025, 12000, 8000, 1}};	// SZD: MPEG25

    /**
     * Constant for MPEG-2 LSF version
     */
    public static final int MPEG2_LSF = 0;
    public static final int MPEG25_LSF = 2;	// SZD

    /**
     * Constant for MPEG-1 version
     */
    public static final int MPEG1 = 1;

    public static final int STEREO = 0;
    public static final int JOINT_STEREO = 1;
    public static final int DUAL_CHANNEL = 2;
    public static final int SINGLE_CHANNEL = 3;
    public static final int FOURTYFOUR_POINT_ONE = 0;
    public static final int FOURTYEIGHT = 1;
    public static final int THIRTYTWO = 2;

    private int h_layer, h_protection_bit, h_bitrate_index,
            h_padding_bit, h_mode_extension;
    private int h_version;
    private int h_mode;
    private int h_sample_frequency;
    private int h_number_of_subbands, h_intensity_stereo_bound;
    private boolean h_copyright, h_original;
    // VBR support added by E.B
    private final double[] h_vbr_time_per_frame = {-1, 384, 1152, 1152};
    private boolean h_vbr;
    private int h_vbr_frames;
    private int h_vbr_scale;
    private int h_vbr_bytes;
    private byte[] h_vbr_toc;

    //private byte syncmode = Bitstream.INITIAL_SYNC;
    //private Crc16 crc;
    public short checksum;
    public int framesize;
    public int nSlots;

    private final int _headerstring = -1; // E.B

    /**
     * Bitrates
     */
    public static final int[][][] BITRATES = {
        {{0 /*free format*/, 32000, 48000, 56000, 64000, 80000, 96000,
            112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0},
        {0 /*free format*/, 8000, 16000, 24000, 32000, 40000, 48000,
            56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0},
        {0 /*free format*/, 8000, 16000, 24000, 32000, 40000, 48000,
            56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}},
        {{0 /*free format*/, 32000, 64000, 96000, 128000, 160000, 192000,
            224000, 256000, 288000, 320000, 352000, 384000, 416000, 448000, 0},
        {0 /*free format*/, 32000, 48000, 56000, 64000, 80000, 96000,
            112000, 128000, 160000, 192000, 224000, 256000, 320000, 384000, 0},
        {0 /*free format*/, 32000, 40000, 48000, 56000, 64000, 80000,
            96000, 112000, 128000, 160000, 192000, 224000, 256000, 320000, 0}},
        // SZD: MPEG2.5
        {{0 /*free format*/, 32000, 48000, 56000, 64000, 80000, 96000,
            112000, 128000, 144000, 160000, 176000, 192000, 224000, 256000, 0},
        {0 /*free format*/, 8000, 16000, 24000, 32000, 40000, 48000,
            56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0},
        {0 /*free format*/, 8000, 16000, 24000, 32000, 40000, 48000,
            56000, 64000, 80000, 96000, 112000, 128000, 144000, 160000, 0}},};

    /**
     * Bitrates in string format
     */
    public static final String[][][] BITRATE_STR = {
        {{"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s",
            "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s",
            "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s",
            "forbidden"},
        {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s",
            "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s",
            "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s",
            "forbidden"},
        {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s",
            "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s",
            "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s",
            "forbidden"}},
        {{"free format", "32 kbit/s", "64 kbit/s", "96 kbit/s", "128 kbit/s",
            "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "288 kbit/s",
            "320 kbit/s", "352 kbit/s", "384 kbit/s", "416 kbit/s", "448 kbit/s",
            "forbidden"},
        {"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s",
            "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "160 kbit/s",
            "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s", "384 kbit/s",
            "forbidden"},
        {"free format", "32 kbit/s", "40 kbit/s", "48 kbit/s", "56 kbit/s",
            "64 kbit/s", "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s",
            "160 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s", "320 kbit/s",
            "forbidden"}},
        // SZD: MPEG2.5
        {{"free format", "32 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s",
            "80 kbit/s", "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s",
            "160 kbit/s", "176 kbit/s", "192 kbit/s", "224 kbit/s", "256 kbit/s",
            "forbidden"},
        {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s",
            "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s",
            "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s",
            "forbidden"},
        {"free format", "8 kbit/s", "16 kbit/s", "24 kbit/s", "32 kbit/s",
            "40 kbit/s", "48 kbit/s", "56 kbit/s", "64 kbit/s", "80 kbit/s",
            "96 kbit/s", "112 kbit/s", "128 kbit/s", "144 kbit/s", "160 kbit/s",
            "forbidden"}},};

    // Functions to query header contents:
    /**
     * Returns version.
     *
     * @return
     */
    public int version() {
        return h_version;
    }

    /**
     * Returns Layer ID.
     *
     * @return
     */
    public int layer() {
        return h_layer;
    }

    /**
     * Returns bitrate index.
     *
     * @return
     */
    public int bitrate_index() {
        return h_bitrate_index;
    }

    /**
     * Returns Sample Frequency.
     *
     * @return
     */
    public int sample_frequency() {
        return h_sample_frequency;
    }

    /**
     * Returns Frequency.
     *
     * @return
     */
    public int frequency() {
        return FREQUENCIES[h_version][h_sample_frequency];
    }

    /**
     * Returns Mode.
     *
     * @return
     */
    public int mode() {
        return h_mode;
    }

    /**
     * Returns Protection bit.
     *
     * @return
     */
    public boolean checksums() {
        return h_protection_bit == 0;
    }

    /**
     * Returns Copyright.
     *
     * @return
     */
    public boolean copyright() {
        return h_copyright;
    }

    /**
     * Returns Original.
     *
     * @return
     */
    public boolean original() {
        return h_original;
    }

    /**
     * Return VBR.
     *
     * @return true if VBR header is found
     */
    public boolean vbr() {
        return h_vbr;
    }

    /**
     * Return VBR scale.
     *
     * @return scale of -1 if not available
     */
    public int vbr_scale() {
        return h_vbr_scale;
    }

    /**
     * Return VBR TOC.
     *
     * @return vbr toc ot null if not available
     */
    public byte[] vbr_toc() {
        return h_vbr_toc;
    }

    /**
     * Returns Checksum flag. Compares computed checksum with stream checksum.
     *
     * @return
     */
    // Seeking and layer III stuff
    /**
     * Returns Layer III Padding bit.
     *
     * @return
     */
    public boolean padding() {
        return h_padding_bit != 0;
    }

    /**
     * Returns Slots.
     *
     * @return
     */
    public int slots() {
        return nSlots;
    }

    /**
     * Returns Mode Extension.
     *
     * @return
     */
    public int mode_extension() {
        return h_mode_extension;
    }

    Header() {
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder(200);
        buffer.append("Layer ");
        buffer.append(layer_string());
        buffer.append(" frame ");
        buffer.append(mode_string());
        buffer.append(' ');
        buffer.append(version_string());
        if (!checksums()) {
            buffer.append(" no");
        }
        buffer.append(" checksums");
        buffer.append(' ');
        buffer.append(sample_frequency_string());
        buffer.append(',');
        buffer.append(' ');
        buffer.append(bitrate_string());

        String s = buffer.toString();
        return s;
    }

    /**
     * Calculate Frame size. Calculates framesize in bytes excluding header
     * size.
     *
     * @return
     */
    public int calculate_framesize() {

        if (h_layer == 1) {
            framesize = (12 * BITRATES[h_version][0][h_bitrate_index])
                    / FREQUENCIES[h_version][h_sample_frequency];
            if (h_padding_bit != 0) {
                framesize++;
            }
            framesize <<= 2;		// one slot is 4 bytes long
            nSlots = 0;
        } else {
            framesize = (144 * BITRATES[h_version][h_layer - 1][h_bitrate_index])
                    / FREQUENCIES[h_version][h_sample_frequency];
            if (h_version == MPEG2_LSF || h_version == MPEG25_LSF) {
                framesize >>= 1;	// SZD
            }
            if (h_padding_bit != 0) {
                framesize++;
            }
            // Layer III slots
            if (h_layer == 3) {
                if (h_version == MPEG1) {
                    nSlots = framesize - ((h_mode == SINGLE_CHANNEL) ? 17 : 32) // side info size
                            - ((h_protection_bit != 0) ? 0 : 2) // CRC size
                            - 4; 								             // header size
                } else {  // MPEG-2 LSF, SZD: MPEG-2.5 LSF
                    nSlots = framesize - ((h_mode == SINGLE_CHANNEL) ? 9 : 17) // side info size
                            - ((h_protection_bit != 0) ? 0 : 2) // CRC size
                            - 4; 								             // header size
                }
            } else {
                nSlots = 0;
            }
        }
        framesize -= 4;             // subtract header size
        return framesize;
    }

    /**
     * Returns the maximum number of frames in the stream.
     *
     * @param streamsize
     * @return number of frames
     */
    public int max_number_of_frames(int streamsize) // E.B
    {
        if (h_vbr == true) {
            return h_vbr_frames;
        } else {
            if ((framesize + 4 - h_padding_bit) == 0) {
                return 0;
            } else {
                return (streamsize / (framesize + 4 - h_padding_bit));
            }
        }
    }

    /**
     * Returns the minimum number of frames in the stream.
     *
     * @param streamsize
     * @return number of frames
     */
    public int min_number_of_frames(int streamsize) // E.B
    {
        if (h_vbr == true) {
            return h_vbr_frames;
        } else {
            if ((framesize + 5 - h_padding_bit) == 0) {
                return 0;
            } else {
                return (streamsize / (framesize + 5 - h_padding_bit));
            }
        }
    }

    /**
     * Returns ms/frame.
     *
     * @return milliseconds per frame
     */
    public float ms_per_frame() // E.B
    {
        if (h_vbr == true) {
            double tpf = h_vbr_time_per_frame[layer()] / frequency();
            if ((h_version == MPEG2_LSF) || (h_version == MPEG25_LSF)) {
                tpf /= 2;
            }
            return ((float) (tpf * 1000));
        } else {
            float ms_per_frame_array[][] = {{8.707483f, 8.0f, 12.0f},
            {26.12245f, 24.0f, 36.0f},
            {26.12245f, 24.0f, 36.0f}};
            return (ms_per_frame_array[h_layer - 1][h_sample_frequency]);
        }
    }

    /**
     * Returns total ms.
     *
     * @param streamsize
     * @return total milliseconds
     */
    public float total_ms(int streamsize) // E.B
    {
        return (max_number_of_frames(streamsize) * ms_per_frame());
    }

    /**
     * Returns synchronized header.
     *
     * @return
     */
    public int getSyncHeader() // E.B
    {
        return _headerstring;
    }

    // functions which return header informations as strings:
    /**
     * Return Layer version.
     *
     * @return
     */
    public String layer_string() {
        switch (h_layer) {
            case 1:
                return "I";
            case 2:
                return "II";
            case 3:
                return "III";
        }
        return null;
    }

    /**
     * Return Bitrate.
     *
     * @return bitrate in bps
     */
    public String bitrate_string() {
        if (h_vbr == true) {
            return Integer.toString(bitrate() / 1000) + " kb/s";
        } else {
            return BITRATE_STR[h_version][h_layer - 1][h_bitrate_index];
        }
    }

    /**
     * Return Bitrate.
     *
     * @return bitrate in bps and average bitrate for VBR header
     */
    public int bitrate() {
        if (h_vbr == true) {
            return ((int) ((h_vbr_bytes * 8) / (ms_per_frame() * h_vbr_frames))) * 1000;
        } else {
            return BITRATES[h_version][h_layer - 1][h_bitrate_index];
        }
    }

    /**
     * Return Instant Bitrate. Bitrate for VBR is not constant.
     *
     * @return bitrate in bps
     */
    public int bitrate_instant() {
        return BITRATES[h_version][h_layer - 1][h_bitrate_index];
    }

    /**
     * Returns Frequency
     *
     * @return frequency string in kHz
     */
    public String sample_frequency_string() {
        switch (h_sample_frequency) {
            case THIRTYTWO:
                switch (h_version) {
                    case MPEG1:
                        return "32 kHz";
                    case MPEG2_LSF:
                        return "16 kHz";
                    // SZD
                    default:
                        return "8 kHz";
                }
            case FOURTYFOUR_POINT_ONE:
                switch (h_version) {
                    case MPEG1:
                        return "44.1 kHz";
                    case MPEG2_LSF:
                        return "22.05 kHz";
                    // SZD
                    default:
                        return "11.025 kHz";
                }
            case FOURTYEIGHT:
                switch (h_version) {
                    case MPEG1:
                        return "48 kHz";
                    case MPEG2_LSF:
                        return "24 kHz";
                    // SZD
                    default:
                        return "12 kHz";
                }
        }
        return (null);
    }

    /**
     * Returns Mode.
     *
     * @return
     */
    public String mode_string() {
        switch (h_mode) {
            case STEREO:
                return "Stereo";
            case JOINT_STEREO:
                return "Joint stereo";
            case DUAL_CHANNEL:
                return "Dual channel";
            case SINGLE_CHANNEL:
                return "Single channel";
        }
        return null;
    }

    /**
     * Returns Version.
     *
     * @return MPEG-1 or MPEG-2 LSF or MPEG-2.5 LSF
     */
    public String version_string() {
        switch (h_version) {
            case MPEG1:
                return "MPEG-1";
            case MPEG2_LSF:
                return "MPEG-2 LSF";
            case MPEG25_LSF:	// SZD
                return "MPEG-2.5 LSF";
        }
        return (null);
    }

    /**
     * Returns the number of subbands in the current frame.
     *
     * @return number of subbands
     */
    public int number_of_subbands() {
        return h_number_of_subbands;
    }

    /**
     * Returns Intensity Stereo. (Layer II joint stereo only). Returns the
     * number of subbands which are in stereo mode, subbands above that limit
     * are in intensity stereo mode.
     *
     * @return intensity
     */
    public int intensity_stereo_bound() {
        return h_intensity_stereo_bound;
    }
}
