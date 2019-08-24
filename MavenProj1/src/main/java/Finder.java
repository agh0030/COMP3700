public class Finder {

    public Integer findMax(int[] intArray)
    {
        if (intArray == null || intArray.length == 0)
        {
            return null;
        }
        int max = intArray[0];
        for (int i = 1; i < intArray.length; i++)
        {
            if (intArray[i] > max)
            {
                max = intArray[i];
            }
        }
        return max;
    }

    public Integer findMin(int[] intArray)
    {
        if (intArray == null || intArray.length == 0)
        {
            return null;
        }
        int min = intArray[0];
        for (int i = 1; i < intArray.length; i++)
        {
            if (intArray[i] < min)
            {
                min = intArray[i];
            }
        }
        return min;
    }


}
