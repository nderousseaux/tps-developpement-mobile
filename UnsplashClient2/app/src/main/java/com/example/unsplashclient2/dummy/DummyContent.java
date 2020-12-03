package com.example.unsplashclient2.dummy;

import android.graphics.Bitmap;

import com.example.unsplashclient2.BitmapDecoderTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<BitmapItem> ITEMS = new ArrayList<BitmapItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, BitmapItem> ITEM_MAP = new HashMap<String, BitmapItem>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(BitmapItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(0, item);
    }

    private static BitmapItem loadBitmap(String url) {
        new BitmapDecoderTask(this).execute(url);

        return new DummyItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A bitmap item representing a piece of content.
     */
    public static class BitmapItem {
        public final String stringUrl;
        public final Bitmap bitmap;

        public BitmapItem(String  stringUrl, Bitmap bitmap) {
            this.stringUrl= stringUrl;
            this.bitmap = bitmap;
        }

    }
}