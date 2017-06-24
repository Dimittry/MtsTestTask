package MtsTest.utils;


public enum SortedParamNames {

    NAME1("name1"),
    NAME2("name2");

    private String sortParam;

    SortedParamNames(final String sortParam) {
        this.sortParam = sortParam;
    }

    public String getSortParam() {
        return sortParam;
    }
}
