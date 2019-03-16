(ns tile-soup.map
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.tileset :as tileset]
            [tile-soup.layer :as layer]))

(s/def ::version string?)
(s/def ::tiledversion string?)
(s/def ::orientation #{"orthogonal"
                       "isometric"
                       "staggered"
                       "hexagonal"})
(s/def ::renderorder #{"right-down"
                       "right-up"
                       "left-down"
                       "left-up"})
(s/def ::width u/str->num)
(s/def ::height u/str->num)
(s/def ::tilewidth u/str->num)
(s/def ::tileheight u/str->num)
(s/def ::hexsidelength u/str->num)
(s/def ::staggeraxis #{"x" "y"})
(s/def ::staggerindex #{"even" "odd"})
(s/def ::backgroundcolor string?)
(s/def ::nextlayerid u/str->num)
(s/def ::nextobjectid u/str->num)

(s/def ::attrs (s/keys :opt-un [::version
                                ::tiledversion
                                ::orientation
                                ::renderorder
                                ::width
                                ::height
                                ::tilewidth
                                ::tileheight
                                ::hexsidelength
                                ::staggeraxis
                                ::staggerindex
                                ::backgroundcolor
                                ::nextlayerid
                                ::nextobjectid]))

(defmulti child :tag)
(defmethod child :tileset [_] ::tileset/tileset)
(defmethod child :layer [_] ::layer/layer)

(s/def ::content (s/coll-of (s/or
                              :tag (s/multi-spec child :tag)
                              :string string?)))

(s/def ::map (s/keys :req-un [::attrs ::content]))

