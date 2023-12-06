//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class VarInt {
    public static final int MAX_VARINT_SIZE = 5;
    public static final int MAX_VARLONG_SIZE = 10;

    private VarInt() {
    }

    public static int varIntSize(int i) {
        int result = 0;

        do {
            ++result;
            i >>>= 7;
        } while(i != 0);

        return result;
    }

    public static int getVarInt(byte[] src, int offset, int[] dst) {
        int result = 0;
        int shift = 0;

        while(shift < 32) {
            int b = src[offset++];
            result |= (b & 127) << shift;
            shift += 7;
            if ((b & 128) == 0) {
                dst[0] = result;
                return offset;
            }
        }

        throw new IndexOutOfBoundsException("varint too long");
    }

    public static int getVarInt(ByteBuffer src) {
        byte tmp;
        if ((tmp = src.get()) >= 0) {
            return tmp;
        } else {
            int result = tmp & 127;
            if ((tmp = src.get()) >= 0) {
                result |= tmp << 7;
            } else {
                result |= (tmp & 127) << 7;
                if ((tmp = src.get()) >= 0) {
                    result |= tmp << 14;
                } else {
                    result |= (tmp & 127) << 14;
                    if ((tmp = src.get()) >= 0) {
                        result |= tmp << 21;
                    } else {
                        result |= (tmp & 127) << 21;

                        for(result |= (tmp = src.get()) << 28; tmp < 0; tmp = src.get()) {
                        }
                    }
                }
            }

            return result;
        }
    }

    public static int getVarInt(InputStream inputStream) throws IOException {
        int result = 0;
        int shift = 0;

        while(shift < 32) {
            int b = inputStream.read();
            result |= (b & 127) << shift;
            shift += 7;
            if ((b & 128) == 0) {
                return result;
            }
        }

        throw new IndexOutOfBoundsException("varint too long");
    }

    public static int putVarInt(int v, byte[] sink, int offset) {
        do {
            int bits = v & 127;
            v >>>= 7;
            byte b = (byte)(bits + (v != 0 ? 128 : 0));
            sink[offset++] = b;
        } while(v != 0);

        return offset;
    }

    public static void putVarInt(int v, ByteBuffer sink) {
        while(true) {
            int bits = v & 127;
            v >>>= 7;
            if (v == 0) {
                sink.put((byte)bits);
                return;
            }

            sink.put((byte)(bits | 128));
        }
    }

    public static void putVarInt(int v, OutputStream outputStream) throws IOException {
        byte[] bytes = new byte[varIntSize(v)];
        putVarInt(v, bytes, 0);
        outputStream.write(bytes);
    }

    public static int varLongSize(long v) {
        int result = 0;

        do {
            ++result;
            v >>>= 7;
        } while(v != 0L);

        return result;
    }

    public static long getVarLong(ByteBuffer src) {
        long tmp;
        if ((tmp = (long)src.get()) >= 0L) {
            return tmp;
        } else {
            long result = tmp & 127L;
            if ((tmp = (long)src.get()) >= 0L) {
                result |= tmp << 7;
            } else {
                result |= (tmp & 127L) << 7;
                if ((tmp = (long)src.get()) >= 0L) {
                    result |= tmp << 14;
                } else {
                    result |= (tmp & 127L) << 14;
                    if ((tmp = (long)src.get()) >= 0L) {
                        result |= tmp << 21;
                    } else {
                        result |= (tmp & 127L) << 21;
                        if ((tmp = (long)src.get()) >= 0L) {
                            result |= tmp << 28;
                        } else {
                            result |= (tmp & 127L) << 28;
                            if ((tmp = (long)src.get()) >= 0L) {
                                result |= tmp << 35;
                            } else {
                                result |= (tmp & 127L) << 35;
                                if ((tmp = (long)src.get()) >= 0L) {
                                    result |= tmp << 42;
                                } else {
                                    result |= (tmp & 127L) << 42;
                                    if ((tmp = (long)src.get()) >= 0L) {
                                        result |= tmp << 49;
                                    } else {
                                        result |= (tmp & 127L) << 49;
                                        if ((tmp = (long)src.get()) >= 0L) {
                                            result |= tmp << 56;
                                        } else {
                                            result |= (tmp & 127L) << 56;
                                            result |= (long)src.get() << 63;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return result;
        }
    }

    public static void putVarLong(long v, ByteBuffer sink) {
        while(true) {
            int bits = (int)v & 127;
            v >>>= 7;
            if (v == 0L) {
                sink.put((byte)bits);
                return;
            }

            sink.put((byte)(bits | 128));
        }
    }

    public static void putVarLong(long v, OutputStream outputStream) throws IOException {
        byte[] bytes = new byte[varLongSize(v)];
        ByteBuffer sink = ByteBuffer.wrap(bytes);
        putVarLong(v, sink);
        outputStream.write(bytes);
    }
}
