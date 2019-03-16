(ns tile-soup.tileset
  (:require [clojure.spec.alpha :as s]
            [tile-soup.image :as image]))

(s/def ::name string?)

(s/def ::attrs (s/keys :opt-un [::name]))

(defmulti child :tag)
(defmethod child :image [_] ::image/image)

(s/def ::content (s/coll-of (s/or
                              :tag (s/multi-spec child :tag)
                              :string string?)))

(s/def ::tileset (s/keys :req-un [::attrs ::content]))

