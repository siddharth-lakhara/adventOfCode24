package day9;

import common.Helper;

import java.util.*;

public class Day9 {

    public void solvePart1() {
        long answer = 0;
        try (Scanner inStream = Helper.readFile("day9_input")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                answer = calculateChecksum(new StringBuffer(line));
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }

    private static long sumTillN(long N) {
        return (N*(N+1))/2;
    }

    private long calculateChecksum(StringBuffer line) {
        long checksum = 0;
        int headIdx = 1;
        int tailIdx = line.length()-1;
        int position = line.charAt(0) - '0';

        while (tailIdx >= headIdx) {
            if (headIdx % 2 == 0) {
                int fileId = headIdx / 2;
                int fileLength = line.charAt(headIdx) - '0';

                long lengthMultiplier = sumTillN(position + fileLength - 1) - sumTillN(position - 1);
                checksum += lengthMultiplier * fileId;

                position += fileLength;
                headIdx++;
            } else {
                int fileId = tailIdx / 2;
                int maxFileLength = line.charAt(tailIdx) - '0';
                int emptySpace = line.charAt(headIdx) - '0';

                int fileLength;
                if (maxFileLength > emptySpace) {
                    fileLength = emptySpace;
                    char update = (char) ((maxFileLength - fileLength) + '0');
                    line.setCharAt(tailIdx, update);
                    headIdx++;
                } else {
                    fileLength = maxFileLength;
                    char update = (char) ((emptySpace - fileLength) + '0');
                    line.setCharAt(headIdx, update);
                    if (line.charAt(headIdx) == '0') {
                        headIdx++;
                    }
                    line.setCharAt(tailIdx, '0');
                    tailIdx -= 2;
                }

                long lengthMultiplier = sumTillN(position + fileLength - 1) - sumTillN(position - 1);
                checksum += lengthMultiplier * fileId;

                position += fileLength;
            }

        }

        return checksum;
    }

    public void solvePart2() {
        long answer = 0;
        try (Scanner inStream = Helper.readFile("day9_example")) {
            while (inStream.hasNextLine()) {
                String line = inStream.nextLine();
                answer = calculateChecksum2(new StringBuffer(line));
            }
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
        }

        System.out.println("Answer: " + answer);
    }

    private long calculateChecksum2(StringBuffer line) {
        HashMap<Integer, LinkedList<Integer>> fileIdCache = populateCache(line);

        long checkSum = 0;
        HashSet<Integer> processedIds = new HashSet<>();
        int position = line.charAt(0) - '0';
        int idx = 1;
        while (idx < line.length()) {
            if (idx%2 == 0) {
                int fileId = idx / 2;
                int fileLength = line.charAt(idx) - '0';
                idx++;
                if (processedIds.contains(fileId)) {
                    position += fileLength;
                    continue;
                }

                long lengthMultiplier = sumTillN(position + fileLength - 1) - sumTillN(position - 1);
                checkSum += lengthMultiplier * fileId;

                position += fileLength;
                processedIds.add(fileId);
            } else {
                int emptySpace = line.charAt(idx) - '0';
                int emptySpaceIdx = idx/2;

                int fileId = fillVoid(emptySpace, emptySpaceIdx, fileIdCache);
                if (fileId == -1) {
                    idx++;
                    continue;
                }
                int fileLength = line.charAt(fileId *2) - '0';
                long lengthMultiplier = sumTillN(position + fileLength - 1) - sumTillN(position - 1);
                checkSum += lengthMultiplier* fileId;

                position += fileLength;
                char leftSpace = (char)((emptySpace-fileLength) + '0');
                line.setCharAt(idx, leftSpace);
                processedIds.add(fileId);

                if (leftSpace == '0') {
                    idx++;
                }
            }
        }

        return checkSum;
    }

    private int fillVoid(int emptySpaceSize, int emptySpaceIdx, HashMap<Integer, LinkedList<Integer>> fileIdCache) {
        int size = emptySpaceSize;
        int maxFileIdx = -1;
        int maxFileSize = 0;

        while (size >= 0) {
            if (fileIdCache.get(size).peekLast() != null) {
                int fill = fileIdCache.get(size).peekLast();
                if (fill < emptySpaceIdx) {
                    fileIdCache.get(size).pop();
                } else {
                    if (fill > maxFileIdx) {
                        maxFileIdx = fill;
                        maxFileSize = size;
                    }
                }
            }
            size--;
        }

        if (maxFileIdx != -1) {
            fileIdCache.get(maxFileSize).pollLast();
        }
        return maxFileIdx;
    }

    private HashMap<Integer, LinkedList<Integer>> populateCache(StringBuffer line) {
        HashMap<Integer, LinkedList<Integer>> fileIdCache = new HashMap<>();
        for (int idx = 0; idx < 10; idx++) {
            fileIdCache.put(idx, new LinkedList<>());
        }

        for (int idx = line.length()-1; idx > 0; idx-=2) {
             int num = line.charAt(idx) - '0';
             fileIdCache.get(num).push(idx/2);
        }

        return fileIdCache;
    }
}

