(setq user-full-name "zhangxu")
(setq user-mail-address "zhangxu@admaster.com.cn")

;;some basic settings
(setq inhibit-startup-message t)

(add-to-list 'load-path "~/.emacs.d/")
;;(add-to-list 'load-path “~/.emacs.d/config/”)

(setq default-tab-width 2)
(setq tab-width 2)

(fset 'yes-or-no-p 'y-or-n-p)

(require 'linum)
(global-linum-mode t)

(show-paren-mode t)

(setq-default make-backup-files nil)

(require 'ido)
(ido-mode t)
(setq ido-enable-flex-matching);;enable fuzzy matching

;; key bindings
(when (eq system-type 'darwin) ;; mac specific settings
  (setq mac-option-modifier 'alt)
  (setq mac-command-modifier 'meta)
  (global-set-key [kp-delete] 'delete-char) ;; sets fn-delete to be right-delete
  )
;; encoding for mac
(setq utf-translate-cjk-mode nil) ; disable CJK coding/encoding (Chinese/Japanese/Korean characters)
(set-language-environment 'utf-8)
(set-keyboard-coding-system 'utf-8-mac) ; For old Carbon emacs on OS X only
(setq locale-coding-system 'utf-8)
(set-default-coding-systems 'utf-8)
(set-terminal-coding-system 'utf-8)
(unless (eq system-type 'windows-nt)
 (set-selection-coding-system 'utf-8))
(prefer-coding-system 'utf-8)

(add-to-list 'load-path "~/.emacs.d/")
(require 'auto-complete-config)
(add-to-list 'ac-dictionary-directories "~/.emacs.d/ac-dict")
(ac-config-default)

(setq load-path (cons (expand-file-name "~/.emacs.d/") load-path))
(require 'go-mode-load)

;;coffee-mode
(add-to-list 'load-path "~/.emacs.d/coffee-mode")
(require 'coffee-mode)
(require 'flymake-easy)
(require 'flymake-coffee)
(add-hook 'coffee-mode-hook 'flymake-coffee-load)

;;jshint
;;(add-to-list 'load-path' "~/.emacs.d/jshint-mode")
;;(require 'flymake-jshint')
;;(add-hook 'javascript-mode-hook
;;					(lambda () (flymake-mode t)))











(put 'upcase-region 'disabled nil)

(put 'downcase-region 'disabled nil)
