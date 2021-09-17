package com.mackenzie.documentalia03.Models;

import java.util.ArrayList;
import java.util.List;

public class Urls {

    public String url;
    private static String demo = "www.google.es";

    public Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static List<Urls> getAllUrls() {
        return new ArrayList<Urls>() {{
            add(new Urls("http://cdn-fms.rbs.com.br/vod/hls_sample1_manifest.m3u8"));  // Nat Geo
            add(new Urls("http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8")); // Nat Geo
            add(new Urls("http://playready.directtaps.net/smoothstreaming/SSWSS720H264/SuperSpeedway_720.ism/Manifest")); // Discovery Channel
            add(new Urls("http://ftp.nluug.nl/pub/graphics/blender/demo/movies/Sintel.2010.720p.mkv")); // Discovery Channel
            add(new Urls("http://playertest.longtailvideo.com/adaptive/wowzaid3/playlist.m3u8")); // DMAX
            add(new Urls("http://sample.vodobox.net/skate_phantom_flex_4k/skate_phantom_flex_4k.m3u8")); // DMAX
            add(new Urls("http://content.jwplatform.com/manifests/vM7nH0Kl.m3u8")); // Odisea
            add(new Urls(demo)); // Odisea
            add(new Urls(demo)); // Historia
            add(new Urls(demo)); // Historia
            add(new Urls(demo)); // Be Mad
            add(new Urls(demo)); // Be Mad
            add(new Urls(demo)); // Mega
            add(new Urls(demo)); // Mega
            add(new Urls(demo)); // NatGeo Wild
            add(new Urls(demo)); // NatGeo Wild
            add(new Urls(demo)); // Caza y Pesca
            add(new Urls(demo)); // Caza y Pesca
            add(new Urls(demo)); // La 2
            add(new Urls(demo)); // La 2
            add(new Urls(demo)); // Crimen e Inves.
            add(new Urls(demo)); // Crimen e Inves.
            add(new Urls(demo)); // Blaze
            add(new Urls(demo)); // Blaze
            add(new Urls(demo)); // Canal Cocina
            add(new Urls(demo)); // Canal Cocina
        }};
    }

    @Override
    public String toString() {
        return url;
    }
}
