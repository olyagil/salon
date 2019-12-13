package by.bsac.salon.utill;

public class PaginationUtil {

    private static final int RECORDS_PER_PAGE = 5;

    public static int getNumOfPages(int rows) {
        int nOfPages = rows / RECORDS_PER_PAGE;
        if (rows % RECORDS_PER_PAGE > 0) {
            nOfPages++;
        }
        return nOfPages;
    }
}
