 package test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;




public class MosaicMain {
	
	public MosaicMain() throws IOException {
       init();
    }
	
	 private Map<String, MosaicArrange> mosaicArrangeMap = new HashMap<>();

	 private void init() throws IOException {
	        File procdir = new File(Config.MOSAIC_PROCESS_IMG);
	        for (File d : procdir.listFiles()) {
	            String k = d.getName();
	            MosaicArrange pieceArrange = new MosaicArrange();
	            for (File pf : d.listFiles()) {
	                pieceArrange.addPiece(pf.getName(), ImageIO.read(pf));
	            }
	            mosaicArrangeMap.put(k, pieceArrange);
	        }
	    }

}
