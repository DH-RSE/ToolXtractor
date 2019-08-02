package eu.dariah.ToolXtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SearchInAbstract
 * @author Yoann
 * @version 1.0
 */
public class SearchInAbstract {
    public static final String REGEX_FIND_WORD = "(?i).*?\\b%s\\b.*?";
    public static final String REGEX_FIND_WORD_WITH_CASE = ".*?\\b%s\\b.*?";

    private List<LinkData> linkAbstractToolList;
    private List<LinkData> linkToolAbstractList;

    public SearchInAbstract() {
        this.linkAbstractToolList = new ArrayList<>();
        this.linkToolAbstractList = new ArrayList<>();
    }

    public void search(List<DHAbstract> dhAbstractList, String toolFilename, String stopwordFilename) throws IOException {
        Set<String> toolnames = ListRetrieverFromFile.getListWordsFromFile(toolFilename);
        Set<String> stopwords = new HashSet<>(0);
        if(stopwordFilename != null) {
            stopwords = ListRetrieverFromFile.getListWordsFromFile(stopwordFilename);
        }
        int size = dhAbstractList.size();
        int i = 0;
        for(DHAbstract dhAbstract : dhAbstractList) {
            System.out.println("Searching in file " + (++i) + " / " + size);
            LinkData linkAbstractTool = new LinkData(dhAbstract.getIdentifier());
            for(String toolname : toolnames) {
                if(! stopwords.contains(toolname)) {
                    if(dhAbstract.getTitle().contains(toolname) || dhAbstract.getDescription().contains(toolname)) {
                        String regex = REGEX_FIND_WORD;
                        if(isMoreUpperCaseAsSpaces(toolname)) { //We should not ignore case
                            regex = REGEX_FIND_WORD_WITH_CASE;
                        }
                        regex = String.format(regex, Pattern.quote(toolname));
                        Pattern p = Pattern.compile(regex, Pattern.DOTALL); //DOTALL option for multiline

                        Matcher m = p.matcher(dhAbstract.getTitle());
                        if(m.matches()) {
                            linkAbstractTool.getMentioned().add(toolname);
                        } else {
                            m = p.matcher(dhAbstract.getDescription());
                            if(m.matches()) {
//                                m.find();
//                            int st = m.start() - 5;
//                            if(st < 0) st = 0;
//                            int end = m.end() + 5;
//                            if(end > dhAbstract.getDescription().length()) end = dhAbstract.getDescription().length();
//                            System.out.println(dhAbstract.getDescription().substring(st, end) + " == " + toolname);
                                linkAbstractTool.getMentioned().add(toolname);
                            }
                        }
                    }
                }
            }
            linkAbstractToolList.add(linkAbstractTool);
        }
    }

    public List<LinkData> getLinkAbstractToolList() {
        return linkAbstractToolList;
    }

    public List<LinkData> getLinkToolAbstractList() {
        createListLinkTookAbstract();
        return linkToolAbstractList;
    }

    private void createListLinkTookAbstract() {
        for(LinkData linkAbstractTool : linkAbstractToolList) {
            for(String toolname : linkAbstractTool.getMentioned()) {
                boolean toolExists = false;
                for(LinkData linkToolAbstract : linkToolAbstractList) {
                    if(linkToolAbstract.getIdentifier().equals(toolname)) {
                        linkToolAbstract.getMentioned().add(linkAbstractTool.getIdentifier());
                        toolExists = true;
                    }
                }
                if(! toolExists) {
                    LinkData linkToolAbstract = new LinkData(toolname);
                    linkToolAbstract.getMentioned().add(linkAbstractTool.getIdentifier());
                    linkToolAbstractList.add(linkToolAbstract);
                }
            }
        }
    }

    private boolean isMoreUpperCaseAsSpaces(String input) {
        int upperCase = 0;
        int space = 0;
        for (int k = 0; k < input.length(); k++) {
            if (Character.isUpperCase(input.charAt(k)))
                upperCase++;
            if (Character.isSpaceChar(input.charAt(k)))
                space++;
        }
        return upperCase > space;
    }
}
