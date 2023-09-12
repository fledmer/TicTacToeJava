public class TicTacToe {
    private enum affiliation{
        EMPTY,
        X,
        O,
        DRAW,
    }
    private affiliation[][] matrix;
    int size;

    boolean isEnd;

    private boolean isXturn = true;
    TicTacToe() {
        matrix = new affiliation[3][3];
        for(int x = 0; x < size; x++){
            matrix[x] = new affiliation[size];
        }
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                matrix[x][y] = affiliation.EMPTY;
            }
        }
    }
    TicTacToe(int size){
        if(size == 0){
            size = 1;
        }
        this.size = size;
        matrix = new affiliation[size][size];
        for(int x = 0; x < size; x++){
            matrix[x] = new affiliation[size];
        }
        for(int y = 0; y < size; y++){
            for(int x = 0; x < size; x++){
                matrix[x][y] = affiliation.EMPTY;
            }
        }
    }

    public String getMap(){
        return matrixToString();
    }

    public String setInput(String str) {
        if(isEnd){
            return "Game is end";
        }
        try{
            var input = parseInput(str);
            setInput(input[0], input[1]);
            var winner = winCheck();
            if (winner != affiliation.EMPTY){
                isEnd = true;
                return matrixToString() + "\n" +winner + " win!";
            }
            return matrixToString();
        } catch (Exception exp){
            return "wrong input \n" + matrixToString();
        }
    }

    private void setInput(int y, int x){
        if (this.matrix[y][x] != affiliation.EMPTY){
            return;
        }
        if (isXturn){
            this.matrix[y][x] = affiliation.X;
        }else{
            this.matrix[y][x] = affiliation.O;
        }
        isXturn = !isXturn;
    }

    private int[] parseInput(@org.jetbrains.annotations.NotNull String input) throws Exception {
        var arr = input.split(",");
        var intArr = new int[2];
        if(arr.length != 2){
            throw new Exception("wrong input");
        }
        intArr[0] = Integer.parseInt(arr[0]);
        intArr[1] = Integer.parseInt(arr[1]);
        return intArr;
    }

    private String matrixToString(){
        var str = "";
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                switch (matrix[y][x]){
                    case EMPTY -> str += "☐";
                    case O -> str += "○";
                    case X -> str += "✘";
                }
            }
            str += "\n";
        }
        return str;
    }

    private affiliation winCheck(){
        var result = horizontalCheck();
        if (result != affiliation.EMPTY){
            return result;
        }
        result = verticalCheck();
        if (result != affiliation.EMPTY){
            return result;
        }
        result = xCheck();
        if (result != affiliation.EMPTY){
            return result;
        }
        result = drawCheck();
        return result;
    }
    private affiliation horizontalCheck(){
        for (int y = 0; y < matrix.length; y++){
            var startValue = matrix[y][0];
            var isFailed = false;
            if(startValue == affiliation.EMPTY){
                break;
            }
            for (int x = 1; x < matrix[y].length; x++){
                if (matrix[y][x] != startValue) {
                    isFailed = true;
                    break;
                }
            }
            if(!isFailed) {
                return startValue;
            }
        }
        return affiliation.EMPTY;
    }

    private affiliation verticalCheck(){
        for (int x = 0; x < size; x++){
            var startValue = matrix[0][x];
            var isFailed = false;
            if(startValue == affiliation.EMPTY){
                break;
            }
            for (int y = 1; y < size; y++){
                if (matrix[y][x] != startValue) {
                    isFailed = true;
                    break;
                }
            }
            if(!isFailed) {
                return startValue;
            }
        }
        return affiliation.EMPTY;
    }

    private affiliation xCheck(){
        var res = firstXcheck();
        if (res != affiliation.EMPTY){
            return res;
        }
        res = secondXcheck();
        return res;
    }

    private  affiliation firstXcheck(){
        var startValue = matrix[0][0];
        if(startValue == affiliation.EMPTY){
            return affiliation.EMPTY;
        }
        for (int x = 0; x < size; x++) {
            if (matrix[x][x] != startValue) {
                return affiliation.EMPTY;
            }
        }
        return startValue;
    }

    private  affiliation secondXcheck(){
        var startValue = matrix[size-1][0];
        if(startValue == affiliation.EMPTY){
            return affiliation.EMPTY;
        }
        for (int x = size-1; x >= 0; x--) {
            if (matrix[x][size-x-1] != startValue) {
                return affiliation.EMPTY;
            }
        }
        return startValue;
    }

    private  affiliation drawCheck(){
        boolean isFind = false;
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                if(matrix[x][y] == affiliation.EMPTY){
                    return affiliation.EMPTY;
                }
            }
        }
        return affiliation.DRAW;
    }
}
