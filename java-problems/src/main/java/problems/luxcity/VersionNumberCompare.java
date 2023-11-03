package problems.luxcity;

/**
 * Given two version numbers, version1 and version2, compare them.
 *
 * Version numbers consist of one or more revisions joined by a dot '.'.
 * Each revision consists of digits and may contain leading zeros.
 * Every revision contains at least one character.
 * Revisions are 0-indexed from left to right, with the leftmost revision
 * being revision 0, the next revision being revision 1, and so on.
 * For example 2.5.33 and 0.1 are valid version numbers.
 *
 * To compare version numbers, compare their revisions in left-to-right order.
 * Revisions are compared using their integer value ignoring any leading zeros.
 * This means that revisions 1 and 001 are considered equal.
 * If a version number does not specify a revision at an index,
 * then treat the revision as 0.
 * For example, version 1.0 is less than version 1.1 because their
 * revision 0s are the same, but their revision 1s are 0 and 1 respectively, and 0 < 1.
 *
 * Return the following:
 *
 * If version1 < version2, return -1.
 * If version1 > version2, return 1.
 * Otherwise, return 0.
 *
 * In the input:
 * v1 - string
 * v2 - string
 *
 *  At the output: number (1 or -1 or 0)
 *
 * Example: 1.
 *
 * v1 = "1.1"
 * v2 = "1.002"
 * getResult( v1, v2 ) --> -1
 *
 * Example: 2.
 *
 * v1 = "2.001"
 * v2 = "2.1.0"
 * getResult( v1, v2 ) --> 0
 */
class VersionNumberCompare {

    public static int getResult(String v1, String v2) {
        String[] strArr1 = v1.split("\\.");
        String[] strArr2 = v2.split("\\.");

        if (strArr1.length > strArr2.length) {
            String[] arr2Temp = new String[strArr2.length + (strArr1.length - strArr2.length)];
            System.arraycopy(strArr2, 0, arr2Temp, 0, strArr2.length);
            for (int i = 0; i < arr2Temp.length; i++) {
                if (arr2Temp[i] == null) {
                    arr2Temp[i] = "0";
                }
            }
            strArr2 = arr2Temp;
        } else if (strArr2.length > strArr1.length) {
            String[] arr1Temp = new String[strArr1.length + (strArr2.length - strArr1.length)];
            System.arraycopy(strArr1, 0, arr1Temp, 0, strArr1.length);
            for (int i = 0; i < arr1Temp.length; i++) {
                if (arr1Temp[i] == null) {
                    arr1Temp[i] = "0";
                }
            }
            strArr1 = arr1Temp;
        }

        int i = 0;
        while (i < strArr1.length) {
            int int1 = Integer.parseInt(strArr1[i]);
            int int2 = Integer.parseInt(strArr2[i]);

            if (int1 > int2) {
                return 1;
            } else if (int1 < int2) {
                return -1;
            }

            i++;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(getResult("1.1.1", "1.001"));
        //System.out.println(getResult("2.001", "2.1.0"));
    }
}
