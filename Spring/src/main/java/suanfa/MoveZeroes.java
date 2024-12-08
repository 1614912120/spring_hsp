package suanfa;

import java.util.Arrays;

public class MoveZeroes {

    //双指针法 快慢指针
    public void moveZeroes3(int[] nums) {
        if(nums == null || nums.length ==0){
            return ;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] !=0) {
                //减少交换的次数
                if(fast != slow) {
                    //减少赋值次数
                    nums[slow] = nums[fast];
                }
                slow++;
            }
        }
        for (; slow < nums.length; slow++) {
            nums[slow] = 0;
        }

    }

    //双指针法 快慢指针
    public void moveZeroes2(int[] nums) {
        if(nums == null || nums.length ==0){
            return ;
        }
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if(nums[fast] !=0) {
                //减少交换的次数
                if(fast != slow) {
                    int temp = nums[fast];
                    nums[fast] = nums[slow];
                    nums[slow] = temp;
                    slow++;
                }
            }
        }

    }


    public int[] moveZeroes(int[] nums) {
        if(nums == null || nums.length ==0){
            return new int[]{};
        }
        int[] temp = new int[nums.length];
        int j=0;
        for(int i=0;i<nums.length;i++) {
            if(nums[i]!=0) {
                temp[j]= nums[i];
                j++;
            }
        }
        return temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,6,0,3,8,0};
        new MoveZeroes().moveZeroes3(nums);
        System.out.println(Arrays.toString(nums));
    }
}

