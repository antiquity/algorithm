#include <algorithm>
#include <iostream>
#include <map>
#include <string>
#include <string_view>
#include <unordered_map>
#include <vector>

int FindWithDefault(const std::unordered_map<char, int>& values, char x) {
  if (x == '0') return 0;
  auto find = values.find(x);
  if (find == values.end()) return -1;
  return find->second;
}

class Rule {
 public:
  Rule(char a, char b, char c) : a_(a), b_(b), c_(c) {}

  std::pair<bool, int> Fits(const std::unordered_map<char, int>& values,
                            int carry) const {
    int a = FindWithDefault(values, a_), b = FindWithDefault(values, b_),
        c = FindWithDefault(values, c_);
    if (carry >= 0) {
      if (a >= 0) {
        if (a + carry == 0 && b_ != c_) return {false, -1};
        if (a + carry > 0 && b_ == c_) return {false, -1};
      }
      if (b >= 0) {
        if (b + carry == 0 && a_ != c_) return {false, -1};
        if (b + carry > 0 && a_ == c_) return {false, -1};
      }
    }
    if (a < 0 || b < 0 || c < 0) {
      return {true, -1};
    }
    if (carry >= 0) {
      int sum = a + b + carry;
      if (sum % 10 == c) {
        return {true, sum / 10};
      } else {
        return {false, -1};
      }
    } else {
      int sum = a + b;
      if (sum % 10 == c) {
        return {true, sum / 10};
      } else if ((sum + 1) % 10 == c) {
        return {true, (sum + 1) / 10};
      } else {
        return {false, -1};
      }
    }
  }

 private:
  char a_, b_, c_;
};

class Rules {
 public:
  Rules(std::string_view a, std::string_view b, std::string_view c) {
    std::size_t min = std::min(std::min(a.size(), b.size()), c.size());
    std::size_t max = std::max(std::max(a.size(), b.size()), c.size());
    if (c.size() < max) {
      std::cout << "Input is invalid!" << std::endl;
      is_valid_ = false;
      return;
    }
    for (int i = 1; i <= max; ++i) {
      rules_.emplace_back(i > a.length() ? '0' : a[a.length() - i],
                          i > b.length() ? '0' : b[b.length() - i],
                          c[c.length() - i]);
    }
    is_valid_ = true;
  }

  bool IsValid() const { return is_valid_; }

  bool Fits(const std::unordered_map<char, int>& values) const {
    int carry = 0;
    for (const Rule& rule : rules_) {
      std::pair<bool, int> result = rule.Fits(values, carry);
      if (!result.first) return false;
      carry = result.second;
    }
    return true;
  }

 private:
  std::vector<Rule> rules_;
  bool is_valid_;
};

void doit(const Rules& rules, const std::vector<char>& alpha, int index,
          std::vector<bool>* used, std::unordered_map<char, int>* values) {
  if (index == alpha.size()) {
    std::string sep = "";
    for (const auto& x : alpha) {
      std::cout << sep << "(" << x << ", " << (*values)[x] << ")";
      sep = " ";
    }
    std::cout << std::endl;
    return;
  }
  char x = alpha[index];
  // std::cout << "doit " << x << std::endl;

  for (int i = 0; i < 9; ++i) {
    if (used->at(i)) continue;
    (*values)[x] = i;
    if (rules.Fits(*values)) {
      if (values->size() > 8) {
        for (const auto& xx : alpha) {
          auto find = values->find(xx);
          if (find == values->end()) continue;
          std::cout << "(" << xx << ", " << find->second << ") ";
        }
        std::cout << "Fits" << std::endl;
      }

      used->at(i) = true;
      doit(rules, alpha, index + 1, used, values);
      used->at(i) = false;
    }
  }
  values->erase(x);
}

void FindIt(std::string_view a, std::string_view b, std::string_view c) {
  std::cout << a << " + " << b << " = " << c << std::endl;
  std::unordered_map<char, int> counter;
  for (char x : a) ++counter[x];
  for (char x : b) ++counter[x];
  for (char x : c) ++counter[x];

  Rules rules(a, b, c);
  if (!rules.IsValid()) return;
  std::vector<char> alpha;
  std::cout << "counter size: " << counter.size() << std::endl;
  for (const auto& x : counter) {
    std::cout << "(" << x.first << ", " << x.second << ")";
    alpha.push_back(x.first);
  }
  std::cout << std::endl;
  std::sort(alpha.begin(), alpha.end(), [&counter](char a, char b) {
    return counter[a] != counter[b] ? counter[a] > counter[b] : a < b;
  });

  for (auto& xx : alpha) std::cout << xx << " ";
  std::cout << std::endl;

  std::vector<bool> used(10);
  std::unordered_map<char, int> values;

  doit(rules, alpha, 0, &used, &values);
}

int main(int argc, char* argv[]) {
  if (argc < 4) return -1;

  FindIt(argv[1], argv[2], argv[3]);
}
