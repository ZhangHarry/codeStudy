package util.tool.score;

import org.eclipse.jdt.core.dom.*;

import java.util.*;

/**
 * Created by Zhanghr on 2016/4/6.
 */
public class ImportVisitor extends ASTVisitor {

    private List<String> imports;
    private List<String> allImports;
    private Map<String, String> importsMap;// name->package
    private String packageName;

    boolean imports_ok = false;
    boolean hasVisited = false;

    public ImportVisitor(){
        imports = new ArrayList<>();
        allImports = new ArrayList<>();
        importsMap = new HashMap<>();
        String packageName = "";
    }

    public List<String> imports() {
        if (hasVisited && !imports_ok){
            imports_ok = true;
            Set set = importsMap.entrySet();
            Iterator<Map.Entry> it = set.iterator();
            while (it.hasNext()) {
                Map.Entry<String, String> entry = it.next();
                imports.add(entry.getValue()+"."+entry.getKey());
            }
        }
        return imports;
    }

    public List<String> getAllImports() {
        return allImports;
    }

    public String getPackageName() {
        return packageName;
    }

    @Override
    public void preVisit(ASTNode node) {
        hasVisited = true;
    }

    @Override
    public boolean visit(ImportDeclaration node) {
        QualifiedName qualifiedName = (QualifiedName)node.getName();
        String name = qualifiedName.getName().toString();
        String qualifier = qualifiedName.getQualifier().toString();
        // if import xx.yy.*, qualifier is xx and name is yy while * is ignored
        if (node.toString().contains("*"))
            allImports.add(qualifier+"."+name);
        else
            importsMap.put(name, qualifier);
        return super.visit(node);
    }

    @Override
    public boolean visit(PackageDeclaration node) {
        packageName = node.getName().toString();
        return super.visit(node);
    }

    @Override
    public boolean visit(SimpleType node) {
        return super.visit(node);
    }

}
