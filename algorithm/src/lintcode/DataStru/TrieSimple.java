package lintcode.DataStru;

/**
 * @author maixiaohai
 * @version V1.0
 * @date 17/4/21
 * @Description 实现一个 Trie，包含 insert, search, 和 startsWith 这三个方法。
 */
public class TrieSimple {
    public static void main(String[] args) {
        Trie trie =  new Trie();
//        trie.insert("lintcode");
//        if (!trie.search("code")) {
//            System.out.println("not exsits word code");
//        } // return false
//        if (trie.startsWith("lint")) {
//            System.out.println("exsits word begin with lint");
//        } // return true
//        if (!trie.startsWith("linterror")) {
//            System.out.println("not exsits word begin with linterror");
//        } // return false
//        trie.insert("linterror");
//        if (trie.search("lintcode")) {
//            System.out.println("exsits word lintcode");
//        } // return true"
//        if (trie.startsWith("linterror")) {
//            System.out.println("exsits word begin with linterror");
//        } // return true
        trie.insert("a");
        if (trie.search("a")) {
            System.out.println("exists word a");
        }
        if (trie.startsWith("a")) {
            System.out.println("exists word begin with a");
        }
    }

    static class TrieNode {
        boolean hasWord;
        TrieNode[] next;
        public TrieNode() {
            next = new TrieNode[26];
            hasWord = false;
        }

        public void insert(String word, int index) {
            if (index == word.length()) {
                hasWord = true;
                return;
            }
            int pos = word.charAt(index) - 'a';
            if (next[pos] == null) {
                next[pos] = new TrieNode();
            }
            next[pos].insert(word, index + 1);
        }

        public TrieNode find(String word, int index) {
            if (index == word.length()) {
                return this;
            }
            int pos = word.charAt(index) - 'a';
            if (next[pos] == null) {
                return null;
            }
            return next[pos].find(word, index + 1);
        }
    }


    public static class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            root.insert(word, 0);
        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = root.find(word, 0);
            return node != null && node.hasWord;
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node = root.find(prefix, 0);
            return node != null;
        }
    }
}
