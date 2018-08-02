package util.tool.score;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import util.FileProcesser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Zhanghr on 2016/8/6.
 */
public class Analyzer {

    private Map<String, List<String>> imports;

    /**
     *
     * @param javaPath a directory or file path
     * @return
     */
    public Map<String, List<String>> getImports(String javaPath) {
        imports = new HashMap<>();
        parseJava(javaPath);
        return imports;
    }

    /**
     * In this method, there is one point we need to consider carefully.
     * We can get relative path of children, maybe we want to process children file,
     * but we can't directly use new File(child_path). Since child_path is a relative path,
     * in fact, File(child_path) is current_dir/child_path rather than expected javaPath/child_path.
     * Therefore, we need to append absolute prefix.
     * We could use dir.listFiles() to get exactly children files, but I want to reduce the time to hold
     * these file with concern for concurrency.
     *
     * @param javaPath directory or file path
     */
    private void parseJava(String javaPath){
        File dir = new File(javaPath);
        if (dir.isDirectory()) {
            String[] children = dir.list();
            String dirAbsolutePath = dir.getAbsolutePath();
            dir = null;
            for (String path : children) {
                path = dirAbsolutePath+"\\"+path;
                File file =new File(path);
                if (file.isDirectory()) {
                    file = null;
                    parseJava(path);
                } else {
                    file = null;
                    parseJavaFile(path);
                }
            }
        }else {
            String path = dir.getAbsolutePath();
            dir = null;
            parseJavaFile(path);
        }
    }

    /**
     * This method only process a java file, so it will filter .java,
     * but it doesn't check whether it is a file or directory.
     *
     * @param filePath path of a file rather than directory
     */
    private void parseJavaFile(String filePath) {
        if (!filePath.endsWith(".java"))
            return;
        String fileName = pickFileName(filePath);
        ASTParser parser = ASTParser.newParser(AST.JLS3);
        parser.setResolveBindings(true);
        parser.setSource(FileProcesser.getContent(filePath).toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        CompilationUnit result = (CompilationUnit) parser.createAST(null);
        ImportVisitor visitor = new ImportVisitor();
        result.accept(visitor);
        List<String> importedClasses = visitor.imports();
        String packageName = visitor.getPackageName();
        imports.put(packageName+","+fileName, importedClasses);
    }

    private String pickFileName(String path) {
        if (path.indexOf("/")>=0)
            return path.substring(path.lastIndexOf("/")+1);
        else if (path.indexOf("\\")>=0)
            return path.substring(path.lastIndexOf("\\")+1);
        else
            return path;
    }

    public static void main(String[] args){
        Analyzer analyzer = new Analyzer();
        Map<String, List<String>> imports = analyzer.getImports("E:\\Java code\\codeExercise\\src\\util\\tool\\score\\ImportVisitor.java");
        imports.toString();
    }
}
