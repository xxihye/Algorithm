package implement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5373 {

    static char[][][] cube = new char[6][3][3];  //6면 3x3
    static char[] color = {'w', 'o', 'b', 'r', 'g', 'y'}; //윗면, 뒷면, 오른쪽면, 앞면, 왼쪽면, 밑면

    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        int n = 0;
        while(t-- > 0){
            set();
            n = Integer.parseInt(br.readLine());
            char[] arr = br.readLine().toCharArray();

            for(int i=0; i<n; i++){
                rotate(arr[i*3], arr[(i*3)+1] == '+');
            }
            print();
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++) sb.append(cube[0][i][j]);
            if(i != 2) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void rotate(char plane, boolean clockwise){
        switch (plane){
            case 'U' :
                //양 옆면들의 0번째 행들이 시계, 반시계 방향으로 회전
                rotateSides(0, clockwise);
                break;
            case 'D' :
                //양 옆면들의 2번째 행들이 반시계, 시계방향으로 회전
                rotateSides(2, !clockwise);
                break;
            case 'F' :
                //윗,아랫면은 2번째 행, 왼쪽면은 2번째열, 오른쪽면 0번째 열들이
                //시계, 반시계 방향으로 회전
                rotateFrontBackSides(2, 0, clockwise);
                break;
            case 'B' :
                //윗,아랫면은 0번째 행, 왼쪽면은 0번째열, 오른쪽면 2번째 열들이
                //반시계, 시계 방향으로 회전
                rotateFrontBackSides(0, 2, !clockwise);
                break;
            case 'L' :
                //윗, 앞면은 0번째 열, 밑,뒷면은 2번째 열 시계, 반시계방향으로 회전
                rotateLeftRightSides(0, 2, clockwise);
                break;
            case 'R' :
                //윗, 앞면은 2번째 열, 밑, 뒷면은 첫번째열 반시계, 시계방향으로 회전
                rotateLeftRightSides(2, 0, !clockwise);
                break;
        }
    }

    public static void rotateLeftRightSides(int num1, int num2, boolean clockwise){
        //윗면(0), 뒷면(1), 앞면(3), 밑면(5)
        char[] tmp = {cube[0][0][num1], cube[0][1][num1], cube[0][2][num1]};
        if(clockwise){
            // 0 2
            //윗면에 뒷면 내용 저장
            cube[0][0][num1] = cube[1][2][num2];
            cube[0][1][num1] = cube[1][1][num2];
            cube[0][2][num1] = cube[1][0][num2];

            //뒷면에 아랫면 내용 저장
            cube[1][0][num2] = cube[5][0][num2];
            cube[1][1][num2] = cube[5][1][num2];
            cube[1][2][num2] = cube[5][2][num2];

            //아랫면에 앞면 내용 저장
            cube[5][0][num2] = cube[3][2][num1];
            cube[5][1][num2] = cube[3][1][num1];
            cube[5][2][num2] = cube[3][0][num1];

            //앞면에 윗면 내용 저장
            cube[3][0][num1] = tmp[0];
            cube[3][1][num1] = tmp[1];
            cube[3][2][num1] = tmp[2];
        }else{
            //0 2
            //반시계 방향
            //윗면에 아면 내용 저장
            cube[0][0][num1] = cube[3][0][num1];
            cube[0][1][num1] = cube[3][1][num1];
            cube[0][2][num1] = cube[3][2][num1];

            //앞면에 밑면 내용 저장
            cube[3][0][num1] = cube[5][2][num2];
            cube[3][1][num1] = cube[5][1][num2];
            cube[3][2][num1] = cube[5][0][num2];

            //밑면에 뒷면 내용 저장
            cube[5][0][num2] = cube[1][0][num2];
            cube[5][1][num2] = cube[1][1][num2];
            cube[5][2][num2] = cube[1][2][num2];

            //뒷면에 윗면 내용 저장
            cube[1][0][num2] = tmp[2];
            cube[1][1][num2] = tmp[1];
            cube[1][2][num2] = tmp[0];
        }
    }


    public static void rotateFrontBackSides(int num1, int num2, boolean clockwise){
        //윗면(0), 오른쪽면(2), 왼쪽면(4), 밑면(5)
        char[] tmp = cube[0][num1];
        if(clockwise){
            //윗면에 왼쪽면 내용 저장
            char[] col1 = {cube[4][2][num1], cube[4][1][num1], cube[4][0][num1]};
            cube[0][num1] = col1;

            //왼족면에 밑면 저장
            cube[4][0][num1] = cube[5][num1][2];
            cube[4][1][num1] = cube[5][num1][1];
            cube[4][2][num1] = cube[5][num1][0];

            //밑면에 오른족면 저장
            char[] col2 = {cube[2][0][num2], cube[2][1][num2], cube[2][2][num2]};
            cube[5][num1] = col2;

            //오른족면에 윗면 저장
            cube[2][0][num2] = tmp[0];
            cube[2][1][num2] = tmp[1];
            cube[2][2][num2] = tmp[2];
        }else{
            //2 0
            //윗면에 오른쪽면 내용 저장
            char[] col1 = {cube[2][0][num2], cube[2][1][num2], cube[2][2][num2]};
            cube[0][num1] = col1;

            //오른쪽면에 밑면 내용 저장
            cube[2][0][num2] = cube[5][num1][0];
            cube[2][1][num2] = cube[5][num1][1];
            cube[2][2][num2] = cube[5][num1][2];

            //밑면에 왼족 내용 저장
            char[] col2 = {cube[4][2][num1], cube[4][1][num1], cube[4][0][num1]};
            cube[5][num1] = col2;

            //왼쪽에 윗면 내영 저장
            cube[4][0][num1] = tmp[2];
            cube[4][1][num1] = tmp[1];
            cube[4][2][num1] = tmp[0];
        }
    }



    public static void rotateSides(int rowNum, boolean clockwise){
        //뒷면(1), 오른쪽면(2), 앞면(3), 왼쪽면(4)
        char[] tmp = cube[3][rowNum];
        if(clockwise){
            //시계방향
            cube[3][rowNum] = cube[2][rowNum];
            cube[2][rowNum] = cube[1][rowNum];
            cube[1][rowNum] = cube[4][rowNum];
            cube[4][rowNum] = tmp;
        }else{
            //반시계방향
            cube[3][rowNum] = cube[4][rowNum];
            cube[4][rowNum] = cube[1][rowNum];
            cube[1][rowNum] = cube[2][rowNum];
            cube[2][rowNum] = tmp;
        }
    }

    public static void set(){
        for(int p=0; p<cube.length; p++)
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    cube[p][i][j] = color[p];
    }
}
