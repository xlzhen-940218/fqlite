package nl.pvanassen.bplist;

import java.io.IOException;
import java.util.List;
import nl.pvanassen.bplist.converter.ConvertToXml;
import nl.pvanassen.bplist.parser.BPListElement;
import nl.pvanassen.bplist.parser.ElementParser;

/* loaded from: fqlite_next.jar:bplist.jar:nl/pvanassen/bplist/BinaryPListParserTest.class */
public class BinaryPListParserTest {
    private final ConvertToXml convetToXml = new ConvertToXml();
    private final ElementParser elementParser = new ElementParser();

    private void test(String baseName) throws IOException {
        List<BPListElement<?>> elements = this.elementParser.parseObjectTable(FileHelper.getFile(baseName + ".bplist"));
        this.convetToXml.convertToXml(elements);
    }

    public void testAirplay() throws IOException {
        test("airplay");
    }

    public void testITunesSmall() throws IOException {
        test("iTunes-small");
    }

    public void testSample1() throws IOException {
        test("sample1");
    }

    public void testSample2() throws IOException {
        test("sample2");
    }

    public void testUID() throws IOException {
        test("uid");
    }

    public void testUTF16() throws IOException {
        test("utf16");
    }
}
