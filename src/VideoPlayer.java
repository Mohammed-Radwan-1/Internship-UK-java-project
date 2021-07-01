package com.google;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Random;

public class VideoPlayer {

  private final VideoLibrary videoLibrary;
  private String currentVideoID;
  private boolean isPaused;
  private HashMap<String, ArrayList> playlists = new HashMap<>();

  public VideoPlayer() {
    this.videoLibrary = new VideoLibrary();
  }

  public void numberOfVideos() {
    System.out.printf("%s videos in the library%n", videoLibrary.getVideos().size());
  }

  public void showAllVideos() {
    for (Video video: videoLibrary.getVideos()){
      System.out.println(video.getDescription());
    }
  }

  public void playVideo(String videoId) {
    if (videoLibrary.getVideo(videoId) == null) {
      System.out.println("Cannot play video: Video does not exist");
      return;
    }
      if (currentVideoID != null) {
        System.out.println("Stopping video: " + videoLibrary.getVideo(currentVideoID).getTitle());
      }
    System.out.println("Playing video: " + videoLibrary.getVideo(videoId).getTitle());
      currentVideoID = videoId;
      isPaused = false;
  }

  public void stopVideo() {
    if (videoLibrary.getVideo(currentVideoID) == null) {
      System.out.println("Cannot stop video: No video is currently playing");
      return;
    } else {
      System.out.println("Stopping video: " + videoLibrary.getVideo(currentVideoID).getTitle());
      currentVideoID = null;
    }
  }

  public void playRandomVideo() {
    Random random = new Random();
    int chance = random.nextInt(videoLibrary.getVideos().size());
    playVideo(videoLibrary.getVideos().get(chance).getVideoId());
  }

  public void pauseVideo() {
    if (videoLibrary.getVideo(currentVideoID) == null) {
      System.out.println("Cannot pause video: No video is currently playing");
    } else if (isPaused) {
      System.out.println("Video already paused: " + videoLibrary.getVideo(currentVideoID).getTitle());
    } else {
      isPaused = true;
      System.out.println("Pausing video: " + videoLibrary.getVideo(currentVideoID).getTitle());
    }
  }

  public void continueVideo() {
    if (videoLibrary.getVideo(currentVideoID) == null) {
      System.out.println("Cannot continue video: No video is currently playing");
    } else if (!isPaused) {
      System.out.println("Cannot continue video: Video is not paused");
    } else {
      isPaused = false;
      System.out.println("Continuing video: " + videoLibrary.getVideo(currentVideoID).getTitle());
    }
  }

  public void showPlaying() {
    Video currentVideo = videoLibrary.getVideo(currentVideoID);
    if (currentVideo == null) {
      System.out.println("No video is currently playing");
      return;
    }
    System.out.print("Currently playing: " + currentVideo.getTitle() + " (" + currentVideo.getVideoId() + ") " + currentVideo.getTags());
    if (isPaused) {
      System.out.println(" - PAUSED");
    } else {
      System.out.println("");
    }
  }

  public void createPlaylist(String playlistName) {
    if (playlists.containsKey(playlistName)) {
      System.out.println("Cannot create playlist: A playlist with the same name already exists");
      return;
    }
    ArrayList newPlaylist = new ArrayList();
    playlists.put(playlistName.toLowerCase(), newPlaylist);
    System.out.println("Successfully created new playlist: " + playlistName);
  }

  public void addVideoToPlaylist(String playlistName, String videoId) {
    Video chosenVideo = videoLibrary.getVideo(videoId);
    if (!playlists.containsKey(playlistName)) {
      System.out.println("Cannot add video to " + playlistName + ": Playlist does not exist");
    } else if (chosenVideo == null) {
      System.out.println("Cannot add video to " + playlistName + ": Video does not exist");
    } else if (playlists.get(playlistName).contains(chosenVideo)) {
      System.out.println("Cannot add video to " + playlistName + ": Video already added");
    } else {
      playlists.get(playlistName).add(chosenVideo);
      System.out.println("Added video to " + playlistName + ": " + chosenVideo.getTitle());
    }
  }

  public void showAllPlaylists() {
    if (playlists.size() == 0) {
      System.out.println("No playlists exist yet");
    }
    System.out.println("Showing all playlists:");
    for (String playlistName: playlists.keySet()) {
      System.out.println(playlistName);
    }
  }

  public void showPlaylist(String playlistName) {
    if (! playlists.containsKey(playlistName)) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
      return;
    }
    System.out.println("Showing playlist: " + playlistName);
    if (playlists.get(playlistName).isEmpty()) {
      System.out.println("No videos here yet");
      return;
    }
    for (Video video: playlists.get(playlistName)) {    //don't know why there's an error here
      System.out.println(video.getDescription());
    }
  }

  public void removeFromPlaylist(String playlistName, String videoId) {
    if (! playlists.containsKey(playlistName)) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
      return;
    }
    Video chosenVideo = videoLibrary.getVideo(videoId);
    if (chosenVideo == null) {
      System.out.println("Cannot remove video from" + playlistName + ": Video does not exist");
    } else if (!playlists.get(playlistName).contains(chosenVideo)) {
      System.out.println("Cannot remove video from" + playlistName + ": Video is not in playlist");
    } else {
      playlists.get(playlistName).remove(chosenVideo);
      System.out.println("Removed video from " + playlistName + ": " + chosenVideo.getTitle());
    }
  }

  public void clearPlaylist(String playlistName) {
    if (! playlists.containsKey(playlistName)) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
      return;
    } else {
      playlists.put(playlistName, new ArrayList());
    }
  }

  public void deletePlaylist(String playlistName) {
    if (! playlists.containsKey(playlistName)) {
      System.out.println("Cannot show playlist " + playlistName + ": Playlist does not exist");
      return;
    } else {
      playlists.remove(playlistName);
    }
  }

  public void searchVideos(String searchTerm) {
    System.out.println("searchVideos needs implementation");
  }

  public void searchVideosWithTag(String videoTag) {
    System.out.println("searchVideosWithTag needs implementation");
  }

  public void flagVideo(String videoId) {
    System.out.println("flagVideo needs implementation");
  }

  public void flagVideo(String videoId, String reason) {
    System.out.println("flagVideo needs implementation");
  }

  public void allowVideo(String videoId) {
    System.out.println("allowVideo needs implementation");
  }
}