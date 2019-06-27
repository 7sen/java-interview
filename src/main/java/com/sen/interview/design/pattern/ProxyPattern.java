package com.sen.interview.design.pattern;

/**
 * <p>
 * 代理模式演示.<br/>
 * 定义：给目标对象提供一个代理对象，并由代理对象控制对目标对象的引用。
 * 类型：结构型模式
 * </p>
 *
 * @author Leo
 * @date 2019-06-25 09:45
 */
public class ProxyPattern {

    public static void main(String[] args) {
        Image image = new ProxyImage("test_10mb.jpg");

        // 图像将从磁盘加载
        image.display();
        System.out.println("");
        // 图像不需要从磁盘加载
        image.display();
    }
}

interface Image {

    void display();
}

class RealImage implements Image {

    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk(fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }

    private void loadFromDisk(String fileName) {
        System.out.println("Loading " + fileName);
    }
}

class ProxyImage implements Image {

    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}