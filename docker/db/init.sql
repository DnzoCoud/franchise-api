-- =========================
-- CLEAN START
-- =========================
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS branches CASCADE;
DROP TABLE IF EXISTS franchises CASCADE;

-- =========================
-- FRANCHISES
-- =========================
CREATE TABLE franchises (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT uq_franchise_name UNIQUE (name)
);

-- =========================
-- BRANCHES
-- =========================
CREATE TABLE branches (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  franchise_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_branch_franchise
      FOREIGN KEY (franchise_id)
          REFERENCES franchises(id)
          ON DELETE CASCADE,

  CONSTRAINT uq_branch_name_per_franchise UNIQUE (name, franchise_id)
);

-- =========================
-- PRODUCTS
-- =========================
CREATE TABLE products (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR(150) NOT NULL,
  stock INTEGER NOT NULL DEFAULT 0,
  branch_id BIGINT NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

  CONSTRAINT fk_product_branch
      FOREIGN KEY (branch_id)
          REFERENCES branches(id)
          ON DELETE CASCADE,

  CONSTRAINT chk_stock_positive CHECK (stock >= 0),

  CONSTRAINT uq_product_name_per_branch UNIQUE (name, branch_id)
);

-- =========================
-- INDEXES
-- =========================

CREATE INDEX idx_branches_franchise_id ON branches(franchise_id);
CREATE INDEX idx_products_branch_id ON products(branch_id);
CREATE INDEX idx_products_stock ON products(stock DESC);

-- =========================
-- TRIGGERS
-- =========================

CREATE OR REPLACE FUNCTION update_timestamp()
RETURNS TRIGGER AS $$
BEGIN
   NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_franchise
BEFORE UPDATE ON franchises
FOR EACH ROW EXECUTE FUNCTION update_timestamp();

CREATE TRIGGER trg_update_branch
BEFORE UPDATE ON branches
FOR EACH ROW EXECUTE FUNCTION update_timestamp();


CREATE TRIGGER trg_update_product
BEFORE UPDATE ON products
FOR EACH ROW EXECUTE FUNCTION update_timestamp();