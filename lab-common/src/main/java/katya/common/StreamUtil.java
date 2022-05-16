package katya.common;

public class StreamUtil {
    public String streamToString(FileInputStream file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file));
        StringBuilder fString = new StringBuilder();
        bufferedReader.readLine();
        String line = bufferedReader.readLine();
        while (line != null) {
            fString.append(line.trim());
            line = bufferedReader.readLine();
        }
        bufferedReader.close();
        return fString.toString();
    }
}
