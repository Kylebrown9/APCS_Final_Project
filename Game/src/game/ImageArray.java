package game;

public class ImageArray {
    String[] paths;
    LightImage[] images;
    
    public ImageArray(String[] paths) {
        this.paths = paths;
        images = new LightImage[paths.length];
        
        for(int i=0; i<paths.length; i++) {
            images[i] = new LightImage(paths[i]);
        }
    }
    
    public LightImage getImage(int index) {
        if(index < images.length)
            return images[index];
        else
            return images[index%paths.length];
    }
}