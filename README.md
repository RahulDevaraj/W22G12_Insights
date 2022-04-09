# W22G12_Insights

IMPORTANT.......
Pre Requisite

1) For Google Sign in to work, an emulator with play services installed is needed
2) For Account settings page to be loaded correctly, finger print must be setup in the emulator. (It can be initialised via settings in the phone)

The steps to install Play services in Nexus 6 is given Below

1. Select any device without an icon "Google Play". Customize everything as you need  

2. Then in the “AVD Manager” menu, click the ▼ icon. In the drop-down list, select: "Show on disk". A folder with the files of the selected device will open.  

3. Open the "config.ini" file from this folder in the editor.  

4. Edit these lines: Line1: PlayStore.enabled = false > PlayStore.enabled = true (this is to display the Google Play icon in the device list of the AVD manager)  

Line2: image.sysdir.1 = system-images\android-30\google_apis\x86\ - > image.sysdir.1 = system-images\android-30\google_apis_playstore\x86\ NOTE: 30 edit with your operating android AVD. 30 for android 11  

Line 3: tag.id=google_apis > tag.id=google_apis_playstore Save the file. 

5. Run the emulator of the device for which the settings were made.
