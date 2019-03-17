(ns tile-soup.data-tag
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(defn- tile [tile]
  (or (some-> tile :attrs :gid u/str->int*)
      0))

(s/def ::content (s/conformer (fn [x]
                                (->> x
                                     (remove string?)
                                     (mapv tile)))))

