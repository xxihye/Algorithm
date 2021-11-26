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
            for(int i=0; i<n; i++) rotate(arr[i*3], arr[(i*3)+1] == '+');
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
        int cnt;
        switch (plane){
            case 'U' :
                //양 옆면들의 0번째 행들이 시계, 반시계 방향으로 회전
                cnt = clockwise ? 1 : 3;
                for(int i=0; i<cnt; i++){
                    rotateSides(0, 0);
                    rotateBorder(0);
                }
                break;

            case 'D' :
                //양 옆면들의 2번째 행들이 반시계, 시계방향으로 회전
                if(clockwise){
                    rotateBorder(5);
                    for(int i=0; i<3; i++)  rotateSides(2, 5);
                }else{
                    for(int i=0; i<3; i++) rotateBorder(5);
                    rotateSides(2, 5);
                }
                break;

            case 'F' :
                //윗,아랫면은 2번째 행, 왼쪽면은 2번째열, 오른쪽면 0번째 열들이 시계, 반시계 방향으로 회전
                cnt = clockwise ? 1 : 3;
                for(int i=0; i<cnt; i++){
                    rotateFrontBackSides(2, 0, 3);
                    rotateBorder(3);
                }
                break;

            case 'B' :
                //윗,아랫면은 0번째 행, 왼쪽면은 0번째열, 오른쪽면 2번째 열들이 반시계, 시계 방향으로 회전
                if(clockwise){
                    rotateBorder(1);
                    for(int i=0; i<3; i++)  rotateFrontBackSides(0, 2, 1);
                }else{
                    rotateFrontBackSides(0, 2, 1);
                    for(int i=0; i<3; i++) rotateBorder(1);
                }
                break;

            case 'L' :
                //윗, 앞면은 0번째 열, 밑,뒷면은 2번째 열 시계, 반시계방향으로 회전
                cnt = clockwise ? 1 : 3;
                for(int i=0; i<cnt; i++){
                    rotateLeftRightSides(0, 2, 4);
                    rotateBorder(4);
                }
                break;

            case 'R' :
                //윗, 앞면은 2번째 열, 밑, 뒷면은 첫번째열 반시계, 시계방향으로 회전
                if(clockwise){
                    rotateBorder(2);
                    for(int i=0; i<3; i++)  rotateLeftRightSides(2, 0, 2);
                }else{
                    rotateLeftRightSides(2, 0, 2);
                    for(int i=0; i<3; i++) rotateBorder(2);
                }
                break;
        }
    }

    public static void rotateLeftRightSides(int num1, int num2, int plane){
        char[] tmp = {cube[0][0][num1], cube[0][1][num1], cube[0][2][num1]};

        for(int i=0; i<3; i++) cube[0][i][num1] = cube[1][2-i][num2]; //윗면에 뒷면 내용 저장
        for(int i=0; i<3; i++) cube[1][i][num2] = cube[5][i][num2]; //뒷면에 아랫면 내용 저장
        for(int i=0; i<3; i++) cube[5][i][num2] = cube[3][2-i][num1]; //아랫면에 앞면 내용 저장
        for(int i=0; i<3; i++) cube[3][i][num1] = tmp[i]; //앞면에 윗면 내용 저장
    }


    public static void rotateFrontBackSides(int num1, int num2, int plane){
        char[] tmp = cube[0][num1];

        for(int i=0; i<3; i++) cube[0][num1][i] = cube[4][2-i][num1]; //윗면에 왼쪽면 내용 저장
        for(int i=0; i<3; i++) cube[4][i][num1] = cube[5][num1][2-i]; //왼족면에 밑면 저장
        for(int i=0; i<3; i++) cube[5][num1][i] = cube[2][i][num2]; //밑면에 오른족면 저장
        for(int i=0; i<3; i++) cube[2][i][num2] = tmp[i]; //오른족면에 윗면 저장
    }

    public static void rotateSides(int rowNum, int plane){
        char[] tmp = cube[3][rowNum];

        cube[3][rowNum] = cube[2][rowNum];
        cube[2][rowNum] = cube[1][rowNum];
        cube[1][rowNum] = cube[4][rowNum];
        cube[4][rowNum] = tmp;
    }

    public static void rotateBorder(int plane){
        char[][] copy = new char[3][3];
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                copy[j][2-i] = cube[plane][i][j];

        cube[plane] = copy;
    }

    public static void set(){
        for(int p=0; p<cube.length; p++)
            for(int i=0; i<3; i++)
                for(int j=0; j<3; j++)
                    cube[p][i][j] = color[p];
    }
}
