import java.lang.Math;


public class Smith_Waterman {
    String sq1, sq2, alphabet;
    int[][] matrix;
    int[][] dp;
    int gap_penalty, score;
    String[] finaloutput;
    int start[] = new int[2];

    public Smith_Waterman( String sq1, String sq2, int[][] matrix, String alphabet, int gap_penalty) {
        this.sq1 = sq1.toUpperCase();
        this.sq2 = sq2.toUpperCase();
        this.matrix = matrix;
        this.alphabet = alphabet;
        this.gap_penalty = gap_penalty;
        this.dp = dynamic();
        this.score = getmaxscore(dp);
        this.finaloutput = getsequences();
    }

    public int[][] dynamic() {  //The main method to calculate the matrix scores for the sequences
        int[][] dp = new int[sq1.length() + 1][sq2.length() + 1];

        for (int i = 0; i < sq1.length(); i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j < sq2.length(); j++) {
            dp[0][j] = 0;
        }
        for (int i = 0; i < sq1.length(); i++) {
            for (int j = 0; j < sq2.length(); j++) {
                dp[i][j] = Math.max(Math.max(Math.max(0, dp[i][j - 1] + gap_penalty), dp[i - 1][j] + gap_penalty), dp[i - 1][j - 1] + getValues(matrix, sq1.charAt(i - 1), sq2.charAt(j - 1), alphabet));
            }
        }
        return dp;

    }

    //Returns the appropriate value from the matrix for the alphabet pair
    public int getValues(int[][] matrix, char a, char b, String s) {
        int index1 = s.indexOf(a);
        int index2 = s.indexOf(b);
        return matrix[index1][index2];
    }

    //Helper function for returning the starting position of row
    public int getmaxrow(int[][] dp) {
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp.length; j++) {
                if (score == dp[i][j]) {
                    return i;
                }
            }
        }
        return -1;
    }

    //Helper function for returning the starting position of column
    public int getmaxcolumn(int[][] dp) {
        for (int j = 0; j < dp.length; j++) {
            for (int i = 0; i < dp.length; i++) {
                if (score == dp[i][j]) {
                    return j;
                }
            }
        }
        return -1;
    }

    //Helper function for returning the max score in the matrix
    public int getmaxscore(int[][] dp){
        int max=0;
        for(int i=0;i<dp.length;i++){
            for(int j=0; j<dp.length;j++){
                max=Math.max(max, dp[i][j]);
            }
        }
        return max;
    }

    public int getScore() {
        return score;
    }

    //Finds the path and gets the output modified sequences
    public String[] getsequences() {
        String outputsq1 = "";
        String outputsq2 = "";
        int i = getmaxrow(dp);
        int j = getmaxcolumn(dp);
        while (i != 0 && j != 0 && dp[i][j] != 0) {
            if (dp[i - 1][j - 1] == dp[i][j] - getValues(matrix, sq1.charAt(i - 1), sq2.charAt(j - 1), alphabet)) {
                outputsq1 = sq1.charAt(i - 1) + outputsq1;
                outputsq2 = sq2.charAt(j - 1) + outputsq2;
                i-=1;
                j-=1;
            } else if (dp[i][j - 1] == dp[i][j] - gap_penalty) {
                outputsq1 = "-" + outputsq1;
                outputsq2 = sq2.charAt(j - 1) + outputsq2;
                j-=1;
            } else {
                outputsq1=sq1.charAt(i-1)+outputsq1;
                outputsq2="-"+outputsq2;
                i-=1;
            }
            start[0]=i;
            start[1]=j;
        }
        return new String []{outputsq1, outputsq2};

    }

    //Helper function for returning the aligned strings
    public String[] getoutputsequences(){
        return finaloutput;
    }

    //Helper function for returning the starting and ending sequence position in the local alignment for 2 sequeneces
    public Integer[] getstart(){
        Integer[] ret = new Integer[4];
        ret[0] = start[0];
        ret[1] = getmaxrow(dp);
        ret[2] = start[1];
        ret[3] = getmaxcolumn(dp);
        return ret;
    }
}



