package com.company;

import java.io.*;

public class GenerateClass{
    private String _className;
    private String[] _variableNames;
    private String[] _variableTypes;

    public GenerateClass(String className, String[] variableNames, String[] variableTypes){
        _className = className;
        _variableNames = variableNames;
        _variableTypes = variableTypes;
        StringBuilder sb = new StringBuilder();
        sb.append("public class " + _className + "{\n");
        sb.append(makeConstructor());
        sb.append(makeGetters());
        sb.append(makeSetters());
        sb.append("}");
        writeFile(sb.toString());
    }

    public String makeConstructor(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _variableTypes.length; i++)
        {
            sb.append("private ");
            sb.append(_variableTypes[i] + " ");
            sb.append(_variableNames[i] + ";");
            sb.append("\n");
        }

        sb.append("\npublic ");
        sb.append(_className);
        sb.append(("("));
        for (int i = 0; i < _variableTypes.length; i++)
        {
            sb.append(_variableTypes[i] + " ");
            sb.append(_variableNames[i]);
            if(i != (_variableTypes.length -1))
                sb.append(", ");
        }
        sb.append(") {\n");
        for (int i = 0; i < _variableTypes.length; i++)
        {
            sb.append("this." + _variableTypes[i]);
            sb.append(" = ");
            sb.append(_variableNames[i] + ";\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    public String makeGetters(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _variableTypes.length; i++)
        {
            sb.append("public " + _variableTypes[i] + " ");
            sb.append("get" + capitalise(_variableNames[i]) + "(){\n");
            sb.append("return " + _variableNames[i] + ";\n}");
            sb.append("\n");
        }
        return sb.toString();
    }

    public String makeSetters(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < _variableTypes.length; i++)
        {
            sb.append("public void set" + capitalise(_variableNames[i]));
            sb.append("(){\n");
            sb.append("this." + _variableNames[i] + " = " + _variableNames[i] +";\n}");
            sb.append("\n");

        }
        return sb.toString();
    }

    public void writeFile(String file){
        try(Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(_className + ".java"), "utf-8"))){
            writer.write(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String capitalise(String str)
    {
        if(str == null || str.length() == 0)
            return "";

        if(str.length() == 1)
            return str.toUpperCase();

        char[] charArray = str.toCharArray();

        charArray[0] = Character.toUpperCase(charArray[0]);

        return new String(charArray);
    }



}
