import java.lang.Math;


public class Smith_Waterman {
    //Variables

    String sq1, sq2;
    Object[][] matrix;
    Object[][] dp;
    int gap_penalty, score;
    String[] finaloutput;

    //Constructor that initialize every variable
    public Smith_Waterman(String sq1, String sq2, Object[][] matrix, int gap_penalty) {
        this.sq1 = sq1.toUpperCase();
        this.sq2 = sq2.toUpperCase();
        this.matrix = matrix;
        this.gap_penalty = gap_penalty;
        this.dp = dynamic();
        this.score = getmaxscore(dp);
        this.finaloutput = getsequences();
    }

    //The main method to calculate the score matrix for the sequences
    public Object[][] dynamic() {
        Object[][] dp = new Object[11][11];

        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                dp[i][j] = 0;
            }
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp.length; j++) {
                if (sq1.charAt(i - 1) == sq2.charAt(j - 1)) {
                    score = 1;
                } else {
                    score = 0;
                }
                //Using equation 1, we implement the score matrix with the maximum
                dp[i][j] = Math.max(Math.max(Math.max(gap_penalty, (int) dp[i - 1][j] + gap_penalty), Math.max(gap_penalty, (int) dp[i][j - 1] + gap_penalty)), Math.max(0, (int) dp[i - 1][j - 1] + score));
            }
        }
        return dp;

    }


    //Helper function for returning the starting position of row
    public int getmaxrow(Object[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (score == (int) dp[i][j]) {
                    return i;
                }
            }
        }
        return -1;
    }

    //Helper function for returning the starting position of column
    public int getmaxcolumn(Object[][] dp) {
        for (int j = 0; j < dp.length; j++) {
            for (int i = 0; i < dp.length; i++) {
                if (score == (int) dp[i][j]) {
                    return j;
                }
            }
        }
        return -1;
    }

    //Helper function for returning the max score in the matrix
    public int getmaxscore(Object[][] dp) {
        int max = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                max = Math.max(max, (int) dp[i][j]);
            }
        }
        return max;
    }

    //Finds the traceback path and gets the output corresponding sequences
    public String[] getsequences() {
        String outputsq1 = "";
        String outputsq2 = "";
        int i = getmaxrow(dp);
        int j = getmaxcolumn(dp);
        while (i != 0 && j != 0 && (int) dp[i][j] != 0) {
            if ((int) dp[i - 1][j - 1] >= (int) dp[i-1][j]) {
                if(sq1.charAt(i-1) != sq2.charAt(j-1)){
                    outputsq1 = "-" + outputsq1;
                    outputsq2 = "-" + outputsq2;
                }
                else{
                    outputsq1 = sq1.charAt(i - 1) + outputsq1;
                    outputsq2 = sq2.charAt(j - 1) + outputsq2;
                }
                i -= 1;
            } else {
                outputsq1 = "-" + outputsq1;
                outputsq2 = sq1.charAt(i - 1) + outputsq2;
            }
            j -= 1;
        }
        return new String[]{outputsq1, outputsq2};

    }

    //Helper function for returning the two aligned sequences
    public String getoutputsequences() {
        return finaloutput[0] +"\n"+finaloutput[1];
    }
}



