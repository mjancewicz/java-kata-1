package org.echocat.kata.java.part1;

import com.google.common.collect.Lists;
import org.supercsv.io.CsvMapReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.supercsv.prefs.CsvPreference.EXCEL_NORTH_EUROPE_PREFERENCE;

public class CsvParser {
    public <T> List<T> parse(InputStream inputStream, Function<Map<String, String>, T> mapper) throws IOException {
        CsvMapReader csvMapReader = new CsvMapReader(new InputStreamReader(inputStream, UTF_8), EXCEL_NORTH_EUROPE_PREFERENCE);
        String[] header = csvMapReader.getHeader(true);
        Map<String, String> rowCells;

        List<T> rows = Lists.newArrayList();
        while ((rowCells = csvMapReader.read(header)) != null) {
            rows.add(mapper.apply(rowCells));
        }

        return rows;
    }
}
