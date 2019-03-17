(ns tile-soup.data-tile
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(s/def ::gid u/str->int)

(s/def ::attrs (s/keys :req-un [::gid]))

(s/def ::data-tile (s/keys :req-un [::attrs]))

