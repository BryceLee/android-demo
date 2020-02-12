package javademo.lizhongxin.javademo.sort;

public class bubblesort {
  public static void main(String[] a){
    int[] todosort=new int[]{4,2,3,1,7,9};
    // for(int i=todosort.length-1;i>=0;i--){
    //     int temp;
    //     for(int j=0;j<=i-1;j++){
    //         if(todosort[j]>todosort[j+1]){
    //             temp=todosort[j];
    //             todosort[j]=todosort[j+1];
    //             todosort[j+1]=temp;
    //         }
    //     }
    // }
    for(int i=0;i<todosort.length;i++){
      System.out.print(todosort[i]);
    }
  }

}
