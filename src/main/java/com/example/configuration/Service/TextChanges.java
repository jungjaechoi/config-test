package com.example.configuration.Service;

import java.util.List;
import java.util.regex.Pattern;

import difflib.DiffRow;
import difflib.DiffRowGenerator;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

@Service
public final class TextChanges {
    private TextChanges() {}

    /**
     * 주어진 두 문자열의 라인 바이 라인 변경사항을 문자열로 리턴한다.
     * @see {{@link #getChanges(List, List)}
     */
    public static String getChanges(String oldValue, String newValue) {
        List<String> oldLines = Lists.newArrayList(Splitter.on(Pattern.compile("\r?\n")).split(oldValue));
        List<String> newlines = Lists.newArrayList(Splitter.on(Pattern.compile("\r?\n")).split(newValue));
        return getChanges(oldLines, newlines);
    }

    /**
     * 주어진 두 문자열 라인 리스트의 차이를 문자열로 리턴한다. 실제 결과는 다음과 같이 된다.
     * <pre>
     * - "loggingSecret" : {
     * -     "hmacSecret" : "deletedHmacSecret"
     * - },
     *   "tokenSecret" : {
     * -     "hmacSecret" : "originalHmacSecret"
     * +     "hmacSecret" : "newHmacSecret"
     *   },
     * </pre>
     */
    public static String getChanges(List<String> oldLines, List<String> newlines) {
        DiffRowGenerator generator = new DiffRowGenerator.Builder().ignoreWhiteSpaces(true).columnWidth(Integer.MAX_VALUE).build();
        List<DiffRow> diffRows = generator.generateDiffRows(oldLines, newlines);

        StringBuilder sb = new StringBuilder();
        for (DiffRow diffrow : diffRows) {
            switch (diffrow.getTag()) {
                case EQUAL: {
                    sb.append(String.format("  %s", diffrow.getOldLine())).append("\n");
                    break;
                }
                case INSERT: {
                    sb.append(String.format("+ %s", diffrow.getNewLine())).append("\n");
                    break;
                }
                case DELETE: {
                    sb.append(String.format("- %s", diffrow.getOldLine())).append("\n");
                    break;
                }
                case CHANGE: {
                    sb.append(String.format("- %s", diffrow.getOldLine())).append("\n");
                    sb.append(String.format("+ %s", diffrow.getNewLine())).append("\n");
                    break;
                }
            }
        }
        return sb.toString();
    }
}
