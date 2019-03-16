(ns tile-soup.tileset
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.image :as image]
            [tile-soup.tileoffset :as tileoffset]
            [tile-soup.grid :as grid]
            [tile-soup.properties :as properties]
            [tile-soup.terraintypes :as terraintypes]
            [tile-soup.tile :as tile]))

(s/def ::firstgid u/str->int)
(s/def ::source string?)
(s/def ::name string?)
(s/def ::tilewidth u/str->int)
(s/def ::tileheight u/str->int)
(s/def ::spacing u/str->int)
(s/def ::margin u/str->int)
(s/def ::tilecount u/str->int)
(s/def ::columns u/str->int)

(s/def ::attrs (s/keys :opt-un [::firstgid
                                ::source
                                ::name
                                ::tilewidth
                                ::tileheight
                                ::spacing
                                ::margin
                                ::tilecount
                                ::columns]))

(defmulti child :tag)
(defmethod child nil [_] any?)
(defmethod child :tileoffset [_] ::tileoffset/tileoffset)
(defmethod child :grid [_] ::grid/grid)
(defmethod child :properties [_] ::properties/properties)
(defmethod child :image [_] ::image/image)
(defmethod child :terraintypes [_] ::terraintypes/terraintypes)
(defmethod child :tile [_] ::tile/tile)
(s/def ::content (s/coll-of (s/multi-spec child :tag)))

(s/def ::tileset (s/keys :req-un [::attrs ::content]))

