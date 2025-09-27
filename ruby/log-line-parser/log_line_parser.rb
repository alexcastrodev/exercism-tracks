class LogLineParser
  def initialize(line)
    @line = line
  end

  def message
    @line.split(': ', 2)[1].strip
  end

  def log_level
    @line.split(']:', 2)[0][1..].downcase
  end

  def reformat
    "#{message} (#{log_level})"
  end
end
