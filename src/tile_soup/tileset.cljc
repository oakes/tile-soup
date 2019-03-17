(ns tile-soup.tileset
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.image :as image]
            [tile-soup.tileoffset :as tileoffset]
            [tile-soup.grid :as grid]
            [tile-soup.properties :as properties]
            [tile-soup.terraintypes :as terraintypes]
            [tile-soup.tile :as tile]
            [tile-soup.wangsets :as wangsets]))

(s/def ::firstgid u/str->int)
(s/def ::source string?)
(s/def ::name string?)
(s/def ::tilewidth u/str->int)
(s/def ::tileheight u/str->int)
(s/def ::spacing u/str->int)
(s/def ::margin u/str->int)
(s/def ::tilecount u/str->int)
(s/def ::columns u/str->int)

(s/def ::attrs (s/keys
                 :opt-un [::firstgid
                          ::source
                          ::name
                          ::tilewidth
                          ::tileheight
                          ::spacing
                          ::margin
                          ::tilecount
                          ::columns]))

(defmulti spec :tag)
(defmethod spec :tileoffset [_] ::tileoffset/tileoffset)
(defmethod spec :grid [_] ::grid/grid)
(defmethod spec :properties [_] ::properties/properties)
(defmethod spec :image [_] ::image/image)
(defmethod spec :terraintypes [_] ::terraintypes/terraintypes)
(defmethod spec :tile [_] ::tile/tile)
(defmethod spec :wangsets [_] ::wangsets/wangsets)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in tileset tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::tileset (s/keys :req-un [::attrs ::content]))

