//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package fqlite.util;

import fqlite.base.GUI;
import fqlite.base.Job;
import fqlite.types.BLOBElement;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.Set;

public class BLOB2Disk {
    Job job;

    public BLOB2Disk(Job job) {
        this.job = job;
    }

    public void export() {
        if (this.job.BLOBs != null) {
            Set<String> keys = this.job.BLOBs.keySet();
            Iterator<String> iter = keys.iterator();

            while(iter.hasNext()) {
                String key = (String)iter.next();
                BLOBElement e = (BLOBElement)this.job.BLOBs.get(key);
                if (e != null) {
                    String extension = ".bin";
                    switch (e.type) {
                        case PNG:
                            extension = ".png";
                            break;
                        case JPG:
                            extension = ".jpg";
                            break;
                        case GIF:
                            extension = ".gif";
                            break;
                        case TIFF:
                            extension = ".tiff";
                            break;
                        case BMP:
                            extension = ".bmp";
                            break;
                        case PDF:
                            extension = ".pdf";
                            break;
                        case HEIC:
                            extension = ".heic";
                            break;
                        case PLIST:
                            extension = ".plist";
                            break;
                        case GZIP:
                            extension = ".gzip";
                            break;
                        default:
                            extension = ".bin";
                    }

                    try {
                        this.job.FileCache.put(key, String.valueOf(GUI.baseDir) + "/" + this.job.filename + "_" + key + extension);
                        BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(String.valueOf(GUI.baseDir) + "/" + this.job.filename + "_" + key + extension));
                        buffer.write(e.binary);
                        buffer.close();
                    } catch (Exception var7) {
                        var7.printStackTrace();
                    }
                }
            }

        }
    }
}
