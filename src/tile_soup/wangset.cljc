(ns tile-soup.wangset
  (:require [clojure.spec.alpha :as s]
            [tile-soup.utils :as u]
            [tile-soup.wangcornercolor :as wangcornercolor]
            [tile-soup.wangedgecolor :as wangedgecolor]
            [tile-soup.wangtile :as wangtile]))

(s/def ::name string?)
(s/def ::tile u/str->long)

(s/def ::attrs (s/keys
                 :req-un [::tile]
                 :opt-un [::name]))

(defmulti spec :tag)
(defmethod spec :wangcornercolor [_] ::wangcornercolor/wangcornercolor)
(defmethod spec :wangedgecolor [_] ::wangedgecolor/wangedgecolor)
(defmethod spec :wangtile [_] ::wangtile/wangtile)
(defmethod spec :default [x]
  (throw (ex-info (str (:tag x) " not supported in wangset tags") {})))
(s/def ::content (u/conformer spec))

(s/def ::wangset (s/keys :req-un [::attrs ::content]))

