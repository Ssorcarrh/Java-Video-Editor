package src.screens.editorScreen.libraryPanel.exportPanel;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;



import src.ImagePanel;
import src.MainApplet;
import src.Project;
import src.renderer.Renderer;
import src.screens.editorScreen.libraryPanel.mediaPanel.MediaPanelManager;
import src.screens.editorScreen.libraryPanel.mediaPanel.album.Album;
import src.screens.editorScreen.libraryPanel.mediaPanel.album.media.MediaAudioItem;
import src.screens.editorScreen.libraryPanel.mediaPanel.album.media.MediaImageItem;
import src.screens.editorScreen.libraryPanel.mediaPanel.album.media.MediaItem;
import src.screens.editorScreen.libraryPanel.mediaPanel.album.media.MediaManager;
import src.screens.editorScreen.newProjectPanel.NewProject;
import src.screens.editorScreen.timeline.track.Track;
import src.util.Misc;
import src.screens.editorScreen.timeline.track.AudioTrack;
import src.screens.editorScreen.timeline.track.trackItem.TrackItem;
import src.screens.editorScreen.timeline.track.trackItem.TrackAudioItem;
import sun.misc.GC;

public class ExportManager {

	
	public static int selectedVideoSizeOption = 0;
	
	public static int outputFormat = 0;
	
	public static int fps = 30;
	
	public static String[] saveToDirectoryFiles = new String[] {"MOV File (*.mov)"};
	public static String[][] directoryExtentsions = new String[][] {{"mov", ".mov"}, {"avi", ".avi"}};
	
	/**
	 * Starts the export process.
	 */
	public static void startExport(){

		

	}
	
	public static void SaveImage(int width, int height, int ai[], String s) {
		BufferedImage bufferedimage = new BufferedImage(width, height, 1);
		bufferedimage.setRGB(0, 0, width, height, ai, 0, width);
		Graphics2D graphics2d = bufferedimage.createGraphics();
		graphics2d.dispose();
		try {
			File picture = new File(s);
			ImageIO.write(bufferedimage, "png", picture);
		} catch (IOException ioexception) {
			System.out.println("error!");
			ioexception.printStackTrace();
		}
	}
	
	/**
	 * Removes any temporary data generated by the export from memory as its no longer needed.
	 */
	public static void cleanup(){


		/*
		 * Store a local version of the global album array-list (To prevent any concurrency issues).
		 */
		ArrayList<Album> tempAlbums = (ArrayList<Album>) MediaPanelManager.albums.clone();

		for(Album album : tempAlbums){

			ArrayList<MediaItem> tempMediaItems = (ArrayList<MediaItem>) album.mediaItems.clone();

			for(MediaItem mediaItem : tempMediaItems){
				if(mediaItem instanceof MediaImageItem){
					MediaImageItem mediaImageItem = (MediaImageItem) mediaItem;
					if(!mediaImageItem.createdPreviewPixels){
						mediaImageItem.sourceImage = null;
					}
				}
			}
		}

	}
	
	/**
	 * Update the states of any components on the export panel.
	 */
	public static void updateVideoSizePanel(){
		
		
		if(selectedVideoSizeOption == 0){
			MainApplet.getInstance().getjRadioButton1().setSelected(true);
		} else {
			MainApplet.getInstance().getjRadioButton1().setSelected(false);
		}
		
		if(selectedVideoSizeOption == 1){
			MainApplet.getInstance().getjRadioButton3().setSelected(true);
			MainApplet.getInstance().getjComboBox2().setEnabled(true);
		} else {
			MainApplet.getInstance().getjRadioButton3().setSelected(false);
			MainApplet.getInstance().getjComboBox2().setEnabled(false);
		}
		
		if(selectedVideoSizeOption == 2){
			MainApplet.getInstance().getjRadioButton4().setSelected(true);
			MainApplet.getInstance().getjTextField4().setEnabled(true);
			MainApplet.getInstance().getjTextField5().setEnabled(true);
			MainApplet.getInstance().getjLabel6().setEnabled(true);
			MainApplet.getInstance().getjLabel7().setEnabled(true);
			MainApplet.getInstance().getjCheckBox3().setEnabled(true);
		} else {
			MainApplet.getInstance().getjRadioButton4().setSelected(false);
			MainApplet.getInstance().getjTextField4().setEnabled(false);
			MainApplet.getInstance().getjTextField5().setEnabled(false);
			MainApplet.getInstance().getjLabel6().setEnabled(false);
			MainApplet.getInstance().getjLabel7().setEnabled(false);
			MainApplet.getInstance().getjCheckBox3().setEnabled(false);
		}
		
	}
	
	public static void exportToGif(){
		
	}
	
	
}
