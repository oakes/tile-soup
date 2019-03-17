(ns tile-soup.data-tag
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]))

(defn- tile [tile]
  (when (= :tile (:tag tile))
    (or (some->> tile :attrs :gid (u/parse u/str->int))
        0)))

(s/def ::content (s/conformer (fn [x]
                                (->> x
                                     (remove string?)
                                     (keep tile)
                                     vec))))

