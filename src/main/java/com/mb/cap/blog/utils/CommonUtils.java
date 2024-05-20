package com.mb.cap.blog.utils;

import org.springframework.util.NumberUtils;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public final class CommonUtils {
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

    public CommonUtils() {
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return obj != null;
    }

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmpty(Object[] array) {
        if (array == null) {
            return true;
        } else {
            return array.length <= 0;
        }
    }

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isEmpty(String s, boolean ignoreSpaces) {
        boolean var10000;
        if (s != null) {
            label29: {
                if (ignoreSpaces) {
                    if (s.trim().isEmpty()) {
                        break label29;
                    }
                } else if (s.isEmpty()) {
                    break label29;
                }

                var10000 = false;
                return var10000;
            }
        }

        var10000 = true;
        return var10000;
    }

    public static boolean isInLength(String s, int minLength, int maxLength) {
        if (minLength < 0) {
            minLength = 0;
        }

        if (maxLength < minLength) {
            maxLength = Integer.MAX_VALUE;
        }

        if (isNull(s)) {
            return false;
        } else {
            int length = s.length();
            return length >= minLength && length <= maxLength;
        }
    }

    public static boolean isPositive(Long val, boolean greaterThanZero) {
        boolean var10000;
        label25: {
            if (val != null) {
                if (greaterThanZero) {
                    if (val > 0L) {
                        break label25;
                    }
                } else if (val >= 0L) {
                    break label25;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }

    public static boolean isPositive(Integer val, boolean greaterThanZero) {
        boolean var10000;
        label25: {
            if (val != null) {
                if (greaterThanZero) {
                    if (val > 0) {
                        break label25;
                    }
                } else if (val >= 0) {
                    break label25;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }

    public static boolean isPositive(Float val, boolean greaterThanZero) {
        boolean var10000;
        label25: {
            if (val != null) {
                if (greaterThanZero) {
                    if (val > 0.0F) {
                        break label25;
                    }
                } else if (val >= 0.0F) {
                    break label25;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }

    public static boolean isPositive(Double val, boolean greaterThanZero) {
        boolean var10000;
        label25: {
            if (val != null) {
                if (greaterThanZero) {
                    if (val > 0.0) {
                        break label25;
                    }
                } else if (val >= 0.0) {
                    break label25;
                }
            }

            var10000 = false;
            return var10000;
        }

        var10000 = true;
        return var10000;
    }

    public static boolean isProgress(Integer val) {
        return val != null && val >= 0 && val <= 100;
    }

    public static int parseInt(String val, final int defaultValue) {
        if (isEmpty(val)) {
            return defaultValue;
        } else {
            int ret = (Integer)NumberUtils.parseNumber(val, Integer.class);
            return ret;
        }
    }

    public static long parseLong(String val, final long defaultValue) {
        if (isEmpty(val)) {
            return defaultValue;
        } else {
            long ret = (Long)NumberUtils.parseNumber(val, Long.class);
            return ret;
        }
    }

    public static float parseFloat(String val, final float defaultValue) {
        if (isEmpty(val)) {
            return defaultValue;
        } else {
            float ret = (Float)NumberUtils.parseNumber(val, Float.class);
            return ret;
        }
    }

    public static double parseDouble(String val, final double defaultValue) {
        if (isEmpty(val)) {
            return defaultValue;
        } else {
            double ret = (Double) NumberUtils.parseNumber(val, Double.class);
            return ret;
        }
    }

    public static Long[] parseLong(String input, String delimiter, boolean dedup) {
        if (!isEmpty(input) && !isEmpty(delimiter)) {
            String[] splits = input.split(delimiter);
            Set<Long> vals = new HashSet();
            String[] var5 = splits;
            int var6 = splits.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                String s = var5[var7];
                Long val = (Long)NumberUtils.parseNumber(s, Long.class);
                if (val > 0L) {
                    if (dedup && !vals.contains(val)) {
                        vals.add(val);
                    } else {
                        vals.add(val);
                    }
                }
            }

            return (Long[])vals.toArray(new Long[0]);
        } else {
            return new Long[0];
        }
    }

    public static Long replaceNull(Long input, long defaultValue) {
        return isNull(input) ? defaultValue : input;
    }

    public static Double replaceNull(Double input, double defaultValue) {
        return isNull(input) ? defaultValue : input;
    }

    public static Integer replaceNull(Integer input, int defaultValue) {
        return isNull(input) ? defaultValue : input;
    }

    public static Float replaceNull(Float input, float defaultValue) {
        return isNull(input) ? defaultValue : input;
    }

    public static String replaceNull(String input, String defaultValue) {
        return isNull(input) ? defaultValue : input;
    }

    public static boolean valueEqual(Integer input1, Integer input2) {
        if (input1 != null && input2 != null) {
            return input1 == input2;
        } else {
            return false;
        }
    }

    public static boolean valueEqual(Long input1, Long input2) {
        if (input1 != null && input2 != null) {
            return input1 == input2;
        } else {
            return false;
        }
    }

    public static String byteCount(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;
        if (bytes < (long)unit) {
            return bytes + " B";
        } else {
            int exp = (int)(Math.log((double)bytes) / Math.log((double)unit));
            String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
            return String.format("%.1f %sB", (double)bytes / Math.pow((double)unit, (double)exp), pre);
        }
    }

    public static String withSuffix(long count) {
        if (count < 1000L) {
            return "" + count;
        } else {
            int exp = (int)(Math.log((double)count) / Math.log(1000.0));
            return String.format("%.1f %c", (double)count / Math.pow(1000.0, (double)exp), "kMGTPE".charAt(exp - 1));
        }
    }
}
