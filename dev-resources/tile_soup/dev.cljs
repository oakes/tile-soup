(ns tile-soup.dev
  (:require [tile-soup.core :as c]
            [dynadoc.core])
  (:require-macros [tile-soup.dev :refer [read-public parse]]
                   [dynadoc.example :refer [defexample defexamples]]))

;(println (pr-str (c/parse (read-public "koalio-b64.tmx"))))
;(println (pr-str (parse "koalio-b64.tmx")))

;(println (pr-str (c/parse (read-public "koalio-xml.tmx"))))
;(println (pr-str (parse "koalio-xml.tmx")))

;(println (pr-str (c/parse (read-public "koalio-csv.tmx"))))
;(println (pr-str (parse "koalio-csv.tmx")))

;(println (pr-str (c/parse (read-public "koalio-b64-gzip.tmx"))))
;(println (pr-str (parse "koalio-b64-gzip.tmx")))

;(println (pr-str (c/parse (read-public "tmx/test_hexagonal_tile_60x60x30.tmx"))))

#_
(doseq [xml [(read-public "tmx/desert.tmx")
             (read-public "tmx/orthogonal-outside.tmx")
             (read-public "tmx/hexagonal-mini.tmx")
             (read-public "tmx/perspective_walls.tmx")
             (read-public "tmx/isometric_grass_and_water.tmx")
             (read-public "tmx/sewers.tmx")
             (read-public "tmx/isometric_staggered_grass_and_water.tmx")
             (read-public "tmx/test_hexagonal_tile_60x60x30.tmx")]]
  (println (pr-str (c/parse xml))))

(defexamples tile-soup.core/parse
  ["Parsing a TMX file whose data is encoded in base 64"
   (parse
     (str "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
       "<map version=\"1.2\" tiledversion=\"1.2.3\" orientation=\"orthogonal\" renderorder=\"right-down\"
       width=\"212\" height=\"20\" tilewidth=\"10\" tileheight=\"2\"
       infinite=\"0\" nextlayerid=\"3\" nextobjectid=\"1\">"
       "<tileset firstgid=\"1\" name=\"tileSet-hd\" tilewidth=\"16\" tileheight=\"16\"
       tilecount=\"0\" columns=\"16\">"
       "<image source=\"tileSet.png\" trans=\"fe80fe\" width=\"256\" height=\"256\"/>"
       "</tileset>"
       "<layer id=\"1\" name=\"background\" width=\"10\" height=\"2\">"
       "<data encoding=\"base64\">"
       "igAAAIsAAACMAAAAjQAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJoAAACbAAAAnAAAAJ0AAAA="
       "</data>"
       "</layer>"
       "</map>"))]
  ["Parsing a TMX file whose data is encoded in CSV"
   (parse
     (str "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
       "<map version=\"1.2\" tiledversion=\"1.2.3\" orientation=\"orthogonal\" renderorder=\"right-down\"
       width=\"212\" height=\"20\" tilewidth=\"10\" tileheight=\"2\"
       infinite=\"0\" nextlayerid=\"3\" nextobjectid=\"1\">"
       "<tileset firstgid=\"1\" name=\"tileSet-hd\" tilewidth=\"16\" tileheight=\"16\"
       tilecount=\"0\" columns=\"16\">"
       "<image source=\"tileSet.png\" trans=\"fe80fe\" width=\"256\" height=\"256\"/>"
       "</tileset>"
       "<layer id=\"1\" name=\"background\" width=\"10\" height=\"2\">"
       "<data encoding=\"csv\">"
       "138,139,140,141,0,0,0,0,0,0,0,0,0,0,0,0,154,155,156,157"
       "</data>"
       "</layer>"
       "</map>"))]
  ["Parsing an TMX file whose data is encoded in XML"
   (parse
     (str "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
       "<map version=\"1.2\" tiledversion=\"1.2.3\" orientation=\"orthogonal\"
       renderorder=\"right-down\" width=\"212\" height=\"20\"
       tilewidth=\"16\" tileheight=\"16\" infinite=\"0\" nextlayerid=\"3\" nextobjectid=\"1\">"
       "<tileset firstgid=\"1\" name=\"tileSet-hd\" tilewidth=\"16\" tileheight=\"16\"
       tilecount=\"256\" columns=\"16\">"
       "<image source=\"tileSet.png\" trans=\"fe80fe\" width=\"256\" height=\"256\"/>"
       "</tileset>"
       "<layer id=\"1\" name=\"background\" width=\"10\" height=\"2\">"
       "<data>"
       "<tile gid=\"138\"/>
       <tile gid=\"139\"/>
       <tile gid=\"140\"/>
       <tile gid=\"141\"/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile/>
       <tile gid=\"154\"/>
       <tile gid=\"155\"/>
       <tile gid=\"156\"/>
       <tile gid=\"157\"/>"
       "</data>"
       "</layer>"
       "</map>"))])

(defexample tile-soup.core/tile-id->map
  "Tile IDs are stored in data tags with some of the highest bits used to store
  information about the flipped state of the tile. You'll need to run the ID through
  this function to get those flipped states, along with the actual usable tile ID.
  
  For more info, see:
  https://doc.mapeditor.org/en/stable/reference/tmx-map-format/#tile-flipping"
  (tile-id->map 268435457))

