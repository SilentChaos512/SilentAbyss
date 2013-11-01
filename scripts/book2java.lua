local tArgs = { ... }

if #tArgs < 1 then
  print("usage: book2java <filename>")
  return
end

function file_exists(file)
  local f = io.open(file, "rb")
  if f then f:close() end
  return f ~= nil
end

function lines_from(file)
  if not file_exists(file) then return {} end
  lines = {}
  for line in io.lines(file) do
    lines[#lines + 1] = line
  end
  return lines
end

local lines = lines_from(tArgs[1])

if #lines < 3 then
  print("Too few lines! Need at least 3.")
  return
end

print('NBTTagCompound tags = new NBTTagCompound();')
print('tags.setString("title", "' .. lines[1] .. '");')
print('tags.setString("author", "' .. lines[2] .. '");')
print('NBTTagList pages = new NBTTagList();')

local page = 1
for i=3,#lines do
  if lines[i] ~= "" then
    print('pages.appendTag(new NBTTagString("' .. page .. '", "' .. lines[i] .. '"));')
    page = page + 1
  end
end

print('tags.setTag("pages", pages);')
print('return tags;')
